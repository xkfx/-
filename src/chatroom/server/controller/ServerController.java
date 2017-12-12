package chatroom.server.controller;

import chatroom.common.Message;
import chatroom.common.VisitorLogin;
import chatroom.server.entity.Visitor;
import chatroom.server.service.MessageService;
import chatroom.server.service.UserService;
import chatroom.server.service.impl.MessageServiceImpl;
import chatroom.server.service.impl.UserServiceImpl;

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
                System.out.println("某个 socket 接入");
                ObjectInputStream inputStream = null;
                ObjectOutputStream outputStream = null;
                try {
                    inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    outputStream = new ObjectOutputStream(socket.getOutputStream());

                    Object object = inputStream.readObject();
                    Message message = (Message) object;
                    if (message.getType() == VISITOR_ACCESS) {
                        VisitorLogin visitorLogin = (VisitorLogin) message;
                        String nickname = visitorLogin.getNickname();
                        Visitor visitor = new Visitor(nickname);

                        messageService.addPublicMessageAcceptor(socket, visitor);

                        // to-do

                        while (true) {
                            object = inputStream.readObject();
                            message = (Message) object;
                            if (message.getType() == PUBLIC_MESSAGE) {
                                messageService.publicMessage(message);
                            }
                        }
                    } else {
                        // 结束
                    }
//                        messageService.publicMessage(message);
//                    } else {
//                        throw new IOException("某个用户登陆失败");
//                    }
//                    System.out.println("某个 socket 完成认证");

                    // 等待客户端的请求
//                    while (true && !socket.isClosed()) {
//                        // TO-DO 客户端主动下线，锁操作。
//                    }

                } catch (ClassNotFoundException notFoundException) {
                    notFoundException.printStackTrace();
                    //messageService.deleteAcceptorById(id);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {

                }
            }
        }).start();
    }
}
