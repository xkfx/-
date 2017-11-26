package chatroom.server.entity.impl;

import chatroom.entity.Message;
import chatroom.server.service.MessageService;
import chatroom.server.service.impl.MessageServiceImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static chatroom.entity.Iconst.PUBLIC_MESSAGE;

public class MyServer {
    private int count = 0;
    private MessageService messageService = new MessageServiceImpl();

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
                ObjectInputStream is = null;
                int id = count++;
                try {
                    messageService.addAcceptor(id, socket);
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

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
                        messageService.publicMessage(new Message(PUBLIC_MESSAGE, "xx用户异常下线.\n"));
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
