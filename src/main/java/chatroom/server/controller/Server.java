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

public class Server {

    private static final int PORT = 10000; // static 的特点是慢 占用空间小，但是因为只用一次就无所谓慢了

    private final MessageService messageService = new MessageServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private ObjectInputStream getObjectInputStream(final Socket socket) throws IOException {
        return new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    // 这里可以修改为 inputStream 但是性质没搞懂暂时不动
    private Message getFirstMessageFromObjectInputStream(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        return (Message) objectInputStream.readObject();
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

    // 这些方法体 oop 角度说应该放 userService ，消息也不该在这里解析,参数也不该那么多，暂时搁在这里，一小步一小步重构
    private void visitorAccess(final Message message, final Socket socket, final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        VisitorLogin visitorLogin = (VisitorLogin) message;
        String nickname = visitorLogin.getNickname();

        Visitor visitor = new Visitor(nickname);
        messageService.addAcceptor(socket, visitor);
        System.out.println("昵称为" + nickname + "的游客等登陆服务器······");

        Message firstResponse = Message.ok("ok");
        messageService.send(socket, firstResponse);

        continuedResponse(objectInputStream, socket);
    }

    private void generalRegister(final Socket socket, final Message message) throws IOException {
        MsgRegister msgRegister = (MsgRegister) message;
        String username = msgRegister.get("username");
        String password = msgRegister.get("password");
        System.out.println(username + "&" + password + "准备注册");
        Register reg = new Register(username, password);
        Message firstResponse = userService.register(reg);
        messageService.send(socket, firstResponse);
    }

    private void generalLogin(final Message message, final Socket socket, final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {

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
            printServerPrompt("用户个人信息已经发出" + user);

            List<User> userList = userService.getFriendList(user.getUserId());
            if (userList != null && userList.size() > 0) {
                messageService.send(socket, new MsgFriends(userList));
                printServerPrompt("用户好友列表已经发出" + userList);
            }
            continuedResponse(objectInputStream, socket);
        }
    }

    // 参数不该这么多。。。
    private void handleFirstMessage(final Socket socket, final Message message, final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        switch (message.getType()) { // 网上说，分支多的话，通常而言 switch要比 if快一点点，但是我是从减少重复代码角度选用 switch 的
            case VISITOR_ACCESS:
                visitorAccess(message, socket, objectInputStream);
                break;
            case GENERAL_REGISTER:
                generalRegister(socket, message);
                break;
            case GENERAL_LOGIN:
                generalLogin(message, socket, objectInputStream);
                break;
            default:
                // to do 引入多态？
                break;
        }
    }

    private void handleExceptionSafely(final Socket socket) { // 这里该放到 finally 还是 exception
        // 合并写成一个 logout socket
        messageService.deleteAcceptor(socket);
        messageService.closeOutputStream(socket);
        userService.logout(socket);
        printServerPrompt(socket.hashCode() + " has quit normally\n");
    }

    private  void closeAllInThread(final Socket socket, ObjectInputStream objectInputStream) throws IOException {
        if (objectInputStream != null) {
            objectInputStream.close();
        }
        socket.close();
    }

    private void createSubThread(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                printServerPrompt("Create a new thread for " + socket.hashCode());
                ObjectInputStream objectInputStream = null;
                try {
                    objectInputStream = getObjectInputStream(socket);
                    printServerPrompt("ObjectInputStream has been created, waiting for the first client message ...");
                    Message message = getFirstMessageFromObjectInputStream(objectInputStream);
                    handleFirstMessage(socket, message, objectInputStream);
                    throw new IOException();
                } catch (ClassNotFoundException | IOException e) {
                    handleExceptionSafely(socket);
                } finally {
                    try {
                        closeAllInThread(socket, objectInputStream);
                    } catch (IOException e) {
                        printServerPrompt(e.getMessage());
                    }
                }
            }
        }).start();
    }

    private void printServerPrompt(final String prompt) {
        System.out.println(prompt);
    }

    private void startWorking(final ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept(); // 关于引用放 while外面还是里面有激烈的争论，我的结论是放里面。
            createSubThread(socket);
        }
    }

    public void startup() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            printServerPrompt("The server is listening on " + PORT + " port ...");
            startWorking(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
