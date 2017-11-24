package chatroom.server.entity;

import chatroom.entity.Message;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static chatroom.entity.Iconst.PUBLIC_MESSAGE;

public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10000);
        System.out.println("服务器准备开始运行");
        while (true) {
            Socket socket = server.accept();
            invoke(socket);
        }
    }

    private static void invoke(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectInputStream is = null;
                try {
                    // 收消息
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    Object object = is.readObject();
                    // 解析与处理
                    Message message = (Message) object;
                    if (message.getType() == PUBLIC_MESSAGE) {
                        System.out.println(message.getContent());
                    } else {
                        System.out.println("无法处理的消息类型");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
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
