package chatroom.client.model;

import chatroom.common.message.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static chatroom.common.message.Iconst.PUBLIC_MESSAGE;

public class ClientMessageService {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private UIManager UIManager;

    public ClientMessageService() {

    }

    public void establishConnection(String host, int port) throws IOException {
        System.out.println("正在创建Socket······");
        socket = new Socket(host, port);
        System.out.println("正在创建输出流······");
        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }


    /**
     * 验证完用户身份后该方法不再用于接收消息，所有来自服务器的消息一律由后台控制器处理。
     * @param message 代发消息
     * @return 除第一次外，其余情况下均为空。
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void send(Message message) throws IOException, ClassNotFoundException {
        outputStream.writeObject(message);
        outputStream.flush();
    }

    public Message sendOnce(Message message) throws IOException, ClassNotFoundException {
        outputStream.writeObject(message);
        outputStream.flush();
        System.out.println("验证消息发送完毕······");

        inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        Message result = (Message) inputStream.readObject();
        System.out.println("等待服务器响应验证······");

        return result;
    }

    public void disconnection() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    public void doResponse() throws IOException, ClassNotFoundException {
        if (socket != null && !socket.isClosed()) {
            // 接收消息
            Object object = inputStream.readObject();
            Message message = (Message) object;
            // 响应消息
            if (UIManager != null && message.getType() == PUBLIC_MESSAGE) {
                UIManager.getChatBox().append(message.getContent() + "\n");
            }
        }
    }

    public void sendPublicMessage(String text) throws IOException {
        if (socket != null) {
            Message message = new Message(PUBLIC_MESSAGE, socket.getInetAddress() + ": " + text);
            // 发送消息
            outputStream.writeObject(message);
            outputStream.flush();
        }
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }
}
