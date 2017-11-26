package chatroom.client.entity.impl;

import chatroom.client.entity.Client;
import chatroom.entity.Message;
import chatroom.ui.service.ComponentManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import static chatroom.entity.Iconst.PUBLIC_MESSAGE;

public class ClientImpl implements Client {
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    private ComponentManager componentManager;

    public ClientImpl(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    @Override
    public void establishConnection(String host, int port) throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    @Override
    public void doResponse() throws IOException, ClassNotFoundException {
        if (socket != null && !socket.isClosed()) {
            // 接收消息
            Object object = is.readObject();
            Message message = (Message) object;
            // 响应消息
            if (message.getType() == PUBLIC_MESSAGE) {
                componentManager.getChatBox().append(message.getContent() + "\n");
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
