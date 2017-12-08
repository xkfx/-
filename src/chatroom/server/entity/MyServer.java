package chatroom.server.entity;

import chatroom.entity.Message;
import chatroom.server.service.MessageService;
import chatroom.server.service.UserService;
import chatroom.server.service.impl.MessageServiceImpl;
import chatroom.server.service.impl.UserServiceImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static chatroom.entity.Iconst.PUBLIC_MESSAGE;
import static chatroom.entity.Iconst.VISITOR_ACCESS;

public class MyServer {
    private int count = 0;
    private MessageService messageService = new MessageServiceImpl();
    private UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        MyServer myServer = new MyServer();
        myServer.startup();
    }

    public void startup() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(10000);
            System.out.println("服务器正在监听 10000 端口 ...");
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
                ObjectInputStream is = null;
                count++;
                int id = socket.hashCode(); // 用户的唯一标识
                try {
                    // 初始化相关服务
                    messageService.addAcceptor(id, socket);

                    // 准备接受第一个请求: 身份认证
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
//                    Object object = is.readObject();
//                    Message message = (Message) object;
//                    if (message.getType() == VISITOR_ACCESS) {
//                        messageService.publicMessage(message);
//                    } else {
//                        throw new IOException("某个用户登陆失败");
//                    }
//                    System.out.println("某个 socket 完成认证");

                    // 等待客户端的请求
                    while (true && !socket.isClosed()) {
                        Object object = is.readObject();
                        Message message = (Message) object;
                        if (message.getType() == PUBLIC_MESSAGE) {
                            messageService.publicMessage(message);
                        }
                        // TO-DO 客户端主动下线，锁操作。
                    }

                } catch (IOException | ClassNotFoundException e) {
                    messageService.deleteAcceptorById(id);
                    try {
                        messageService.publicMessage(new Message(PUBLIC_MESSAGE, e.getMessage()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } finally {
                    try {
                        if (is != null) {
                            is.close();
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
