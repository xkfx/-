package chatroom.server.controller;

import chatroom.common.Message;
import chatroom.common.VisitorLogin;
import chatroom.server.entity.Visitor;
import chatroom.server.model.MessageService;
import chatroom.server.model.impl.MessageServiceImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static chatroom.common.Iconst.PUBLIC_MESSAGE;
import static chatroom.common.Iconst.VISITOR_ACCESS;

public class ServerController {
    private MessageService messageService = new MessageServiceImpl();

    public void startup() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(10001);
            System.out.println("服务器正在监听 10001 端口 ...");
            while (true) {
                Socket socket = server.accept();
                serve(socket);
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
                        messageService.addPublicMessageAcceptor(socket, visitor);
                        Message firstResponse = new Message();
                        firstResponse.setFlag(true);
                        messageService.send(socket, firstResponse);
                        System.out.println("准备持续为此Socket提供服务······");
                        while (true) {
                            object = inputStream.readObject();
                            Message message = (Message) object;
                            if (message.getType() == PUBLIC_MESSAGE) {
                                messageService.publicMessage(socket, message);
                            }
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (ClassNotFoundException notFoundException) {
                    messageService.deleteAcceptorBySocket(socket);
                    System.out.println(socket.hashCode() + "已正常下线。ClassNotFound");
                } catch (IOException ioException) {
                    messageService.deleteAcceptorBySocket(socket);
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
