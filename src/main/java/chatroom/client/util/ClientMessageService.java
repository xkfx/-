package chatroom.client.util;

import chatroom.common.message.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMessageService {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void send(Message message) throws IOException, ClassNotFoundException {
        outputStream.writeObject(message);
        outputStream.flush();
    }

    public Message sendOnce(Message message) throws IOException, ClassNotFoundException {
        outputStream.writeObject(message);
        outputStream.flush();
        System.out.println("验证消息发送完毕······");

        inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        printClientPrompt("Waiting for server response ...");
        Message result = (Message) inputStream.readObject();
        System.out.println("等待服务器响应验证······");

        return result;
    }

    private void printClientPrompt(String s) {
    }

    public void establishConnection(String host, int port) throws IOException {
        System.out.println("正在创建Socket······");
        socket = new Socket(host, port);
        System.out.println("正在创建输出流······");
        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

}
