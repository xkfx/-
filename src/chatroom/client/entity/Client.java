package chatroom.client.entity;

import chatroom.entity.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static chatroom.entity.Iconst.PUBLIC_MESSAGE;

public class Client {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i != 100; ++i) {
            Socket socket = null;
            ObjectOutputStream os = null;
            try {
                socket = new Socket("localhost", 10000);
                // 发消息
                os = new ObjectOutputStream(socket.getOutputStream());
                Message message = new Message(PUBLIC_MESSAGE, "message number " + i);
                os.writeObject(message);
                os.flush();

            } finally {
                try {
                    os.close();
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
    }

    public void sendMessage() {

    }
}
