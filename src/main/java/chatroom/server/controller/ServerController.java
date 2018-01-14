package chatroom.server.controller;

import chatroom.common.entity.User;
import chatroom.common.entity.Visitor;
import chatroom.common.message.*;
import chatroom.server.dto.Login;
import chatroom.server.dto.Register;
import chatroom.server.model.MessageService;
import chatroom.server.model.UserService;
import chatroom.server.model.impl.MessageServiceImpl;
import chatroom.server.model.impl.UserServiceImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static chatroom.common.message.Iconst.*;

public class ServerController {

    private static final int PORT = 10000; // static 的特点是慢 占用空间小，但是因为只用一次就无所谓慢了

    private final MessageService messageService = new MessageServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private ObjectInputStream getObjectInputStream(final Socket socket) throws IOException {
        ObjectInputStream result = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        return result;
    }

    private Message getFirstMessage(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Object object = objectInputStream.readObject();
        Message result = (Message) object;
        return result;
    }

    private void continuedResponse(ObjectInputStream inputStream, Socket socket) throws IOException, ClassNotFoundException {
        System.out.println("准备持续为此Socket提供服务······");
        final int contentSize = 32; // 一般单条消息的长度是 20 个汉字以内，当 > 16 时会成倍扩张，所以干脆直接申请 32 个字节，以提高性能

        while (true) {
            Object object = inputStream.readObject();
            Message message = (Message) object;



            if (message.getType() == PUBLIC_MESSAGE) {
                messageService.sendAll(socket, message);
            }

            if (message.getType() == PERSONAL_MESSAGE) {
                // 分两种情况：对方在线和对方不再线
                Long target = message.getTarget();
                if (userService.getSocket(target) != null) {
                    // 在线转发
                    Socket targetSocket = userService.getSocket(target);
                    final String originContent = message.getContent(); // 不加 final 只是传引用！暂时还没有思考其它方法。
                    StringBuilder builder = new StringBuilder(contentSize);
                    builder.append(userService.getUser(message.getSource()).getNickname() + "悄悄对你说：");
                    builder.append(originContent + "\n");

                    message.setContent(builder.toString());
                    messageService.send(targetSocket, message);

                    StringBuilder echo = new StringBuilder(contentSize);
                    echo.append("你悄悄对" + userService.getUser(target).getNickname() + "说：");
                    echo.append(originContent + "\n");

                    message.setContent(echo.toString());
                    messageService.send(socket, message);
                } else {
                    message.setContent("对方不在线哦！\n");
                    messageService.send(socket, message);
                    // 不在线缓存消息

                }
            }
        }
    }

    private void createServerThread(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                printPrompt("Create a new thread for " + socket.hashCode());
                ObjectInputStream objectInputStream = null;
                try {
                    objectInputStream = getObjectInputStream(socket);
                    printPrompt("ObjectInputStream has been created, waiting for the first client message ...");
                    Message message = getFirstMessage(objectInputStream);

                    // 游客
                    if (message.getType() == VISITOR_ACCESS) {
                        VisitorLogin visitorLogin = (VisitorLogin) message;
                        String nickname = visitorLogin.getNickname();

                        Visitor visitor = new Visitor(nickname);
                        messageService.addAcceptor(socket, visitor);
                        System.out.println("昵称为" + nickname + "的游客等登陆服务器······");

                        Message firstResponse = Message.ok("ok");
                        messageService.send(socket, firstResponse);

                        continuedResponse(objectInputStream, socket);
                    }

                    // 注册
                    if (message.getType() == GENERAL_REGISTER) {

                        MsgRegister msgRegister = (MsgRegister) message;
                        String username = msgRegister.get("username");
                        String password = msgRegister.get("password");

                        System.out.println(username + "&" + password + "准备注册");

                        Register reg = new Register(username, password);
                        Message firstResponse = userService.register(reg);

                        messageService.send(socket, firstResponse);
                    }

                    // 登陆
                    if (message.getType() == GENERAL_LOGIN) {

                        MsgLogin msgLogin = (MsgLogin) message;
                        String username = msgLogin.get("username");
                        String password = msgLogin.get("password");

                        System.out.println(username + "&" + password + "准备登陆");

                        Login login = new Login(username, password);
                        Message firstResponse = userService.login(socket, login);

                        messageService.send(socket, firstResponse);

                        // 初始化信息: 个人信息， 好友列表/状态， 缓存消息
                        if (firstResponse.getFlag()) {
                            User user = userService.getUser(socket);
                            messageService.addAcceptor(socket, user);
                            messageService.send(socket, new MsgProfile(user));
                            System.out.println("用户个人信息已经发出" + user);

                            List<User> userList = userService.getFriendList(user.getUserId());
                            if (userList != null && userList.size() > 0) {
                                messageService.send(socket, new MsgFriends(userList));
                                System.out.println("用户好友列表已经发出" + userList);
                            }
                            continuedResponse(objectInputStream, socket);
                        }
                    }

                    throw new IOException();

                } catch (ClassNotFoundException notFoundException) {
                    messageService.deleteAcceptor(socket);
                    messageService.closeOutputStream(socket);
                    userService.logout(socket);
                    System.out.println(socket.hashCode() + "已正常下线。ClassNotFound\n");
                } catch (IOException ioException) {
                    messageService.deleteAcceptor(socket);
                    messageService.closeOutputStream(socket);
                    userService.logout(socket);
                    System.out.println(socket.hashCode() + "已正常下线。IOException\n");
                } finally {
                    try {
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private ServerSocket createServerSocket(final int port) throws IOException {
        ServerSocket result = new ServerSocket(port);
        return result;
    }

    private void printPrompt(final String prompt) {
        System.out.println(prompt);
    }

    private void startWorking(final ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept(); // 关于引用放 while外面还是里面有激烈的争论，我的结论是放里面。
            createServerThread(socket);
        }
    }

    public void startup() {
        try {
            ServerSocket serverSocket = createServerSocket(PORT);
            printPrompt("The server is listening on " + PORT + " port ...");
            startWorking(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
