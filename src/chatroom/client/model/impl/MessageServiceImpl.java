package chatroom.client.model.impl;

import chatroom.client.model.MessageService;
import chatroom.common.Message;
import chatroom.client.model.UIManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static chatroom.common.Iconst.PUBLIC_MESSAGE;

public class MessageServiceImpl implements MessageService {
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    private UIManager UIManager;

    public MessageServiceImpl() {

    }

    public MessageServiceImpl(UIManager UIManager) {
        this.UIManager = UIManager;
    }

    @Override
    public void establishConnection(String host, int port) throws IOException {
        socket = new Socket(host, port);
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    @Override
    public Message send(Message message) {
        return null;
    }

    @Override
    public void doResponse() throws IOException, ClassNotFoundException {
        if (socket != null && !socket.isClosed()) {
            // 接收消息
            Object object = is.readObject();
            Message message = (Message) object;
            // 响应消息
            if (UIManager != null && message.getType() == PUBLIC_MESSAGE) {
                UIManager.getChatBox().append(message.getContent() + "\n");
            }
        }
    }

    @Override
    public void sendPublicMessage(String text) throws IOException {
        if (socket != null) {
            Message message = new Message(PUBLIC_MESSAGE, socket.getInetAddress() + ": " + text);
            // 发送消息
            os.writeObject(message);
            os.flush();
        }
    }

    @Override
    public void shutdown() throws IOException {
        os.close();
        is.close();
        socket.close();
    }

    @Override
    public void sendOfflineRequest() {

    }
}
