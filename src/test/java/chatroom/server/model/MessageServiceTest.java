package chatroom.server.model;

import chatroom.common.message.Message;
import chatroom.server.model.impl.MessageServiceImpl;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class MessageServiceTest {

    private MessageService messageService = new MessageServiceImpl();

    /**
     * 测试 messageService 的 send 方法
     */
    @Test
    public void contentShouldBeSentNormally() {
        final String CONTENT_BE_SENT = " Hello 你好！";
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(8000);
                    Socket socket = serverSocket.accept();
                    // 测试 send 方法
                    messageService.send(socket, new Message(CONTENT_BE_SENT));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        String result = null;
        try {
            Socket socket = new Socket("127.0.0.1", 8000);
            ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Message message = (Message) inputStream.readObject();
            result = message.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals("CONTENT CAN'T BE SENT NORMALLY!", CONTENT_BE_SENT, result);
    }
}
