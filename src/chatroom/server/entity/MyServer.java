package chatroom.server.entity;

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
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10000);
        System.out.println("服务器正在监听 10000 端口 ...");
        MessageService messageService = new MessageServiceImpl();
        while (true) {
            Socket socket = server.accept();
            messageService.addAcceptor(socket);
            invoke(socket, messageService);
        }
    }

    private static void invoke(final Socket socket, MessageService messageService) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectInputStream is = null;
                try {
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

                    // 等待客户端的请求
                    while (true) {
                        Object object = is.readObject();
                        Message message = (Message) object;
                        if (message.getType() == PUBLIC_MESSAGE) {
                            messageService.publicMessage(message);
                        }
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
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
