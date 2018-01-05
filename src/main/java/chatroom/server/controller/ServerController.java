package chatroom.server.controller;

import chatroom.common.Message;
import chatroom.common.VisitorLogin;
import chatroom.server.dto.Register;
import chatroom.server.entity.Visitor;
import chatroom.server.model.MessageService;
import chatroom.server.model.UserService;
import chatroom.server.model.impl.MessageServiceImpl;
import chatroom.server.model.impl.UserServiceImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static chatroom.common.Iconst.PUBLIC_MESSAGE;
import static chatroom.common.Iconst.VISITOR_ACCESS;

public class ServerController {
    private MessageService messageService = new MessageServiceImpl();
    private UserService userService = new UserServiceImpl();

    public void startup() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10001);
            System.out.println("服务器正在监听 10001 端口 ...");
            while (true) {
                serve(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serve(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(socket.hashCode() + "接入服务器······");

                ObjectInputStream inputStream = null;
                try {
                    inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    System.out.println("服务器输入流创建完毕，等待客户端的第一条消息······");
                    // 响应该 socket 的第一条消息
                    Object object = inputStream.readObject();
                    Message firstRequest = (Message) object;
                    if (firstRequest.getType() == VISITOR_ACCESS) {
                        VisitorLogin visitorLogin = (VisitorLogin) firstRequest;
                        String nickname = visitorLogin.getNickname();
                        Visitor visitor = new Visitor(nickname);
                        System.out.println("昵称为" + nickname + "的游客等登陆服务器······");
                        messageService.addAcceptor(socket, visitor);
                        Message firstResponse = new Message();
                        firstResponse.setFlag(true);
                        messageService.send(socket, firstResponse);
                        System.out.println("准备持续为此Socket提供服务······");
                        while (true) {
                            object = inputStream.readObject();
                            Message message = (Message) object;
                            if (message.getType() == PUBLIC_MESSAGE) {
                                messageService.sendAll(socket, message);
                            }
                            if (false) {
                                String username = message.get("username");
                                String password = message.get("password");
                                Register reg = new Register(username, password);
                                Message regMessage = userService.register(reg);
                                messageService.send(socket, regMessage);
                            }
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (ClassNotFoundException notFoundException) {
                    messageService.deleteAcceptor(socket);
                    System.out.println(socket.hashCode() + "已正常下线。ClassNotFound");
                } catch (IOException ioException) {
                    messageService.deleteAcceptor(socket);
                    System.out.println(socket.hashCode() + "已正常下线。IOException");
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
