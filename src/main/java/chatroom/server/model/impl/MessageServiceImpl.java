package chatroom.server.model.impl;

import chatroom.common.Message;
import chatroom.server.entity.Visitor;
import chatroom.server.model.MessageService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static chatroom.common.Iconst.PUBLIC_MESSAGE;

public class MessageServiceImpl implements MessageService {
    private Map<Socket, Visitor> socketVisitorMap = new HashMap<>();

    @Override
    public void addPublicMessageAcceptor(Socket socket, Visitor visitor) {
        socketVisitorMap.put(socket, visitor);
    }

    @Override
    public void deleteAcceptorBySocket(Socket socket) {
        socketVisitorMap.remove(socket);
    }

    @Override
    public void publicMessage(Socket socket, Message message) throws IOException {
        if (message.getType() == PUBLIC_MESSAGE) {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("【游客】"); // 身份
            messageBuilder.append(socketVisitorMap.get(socket).getNickname() + ":  "); // 昵称
            messageBuilder.append(message.getContent() + "\n"); // 正文
            message.setContent(messageBuilder.toString());
            for (Socket eachSocket : socketVisitorMap.keySet()) {
                ObjectOutputStream outputStream = new ObjectOutputStream(eachSocket.getOutputStream());
                outputStream.writeObject(message);
                outputStream.flush();
            }
        }
    }

    @Override
    public void send(Socket socket, Message message) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(message);
        outputStream.flush();
        System.out.println("消息" + message.getFlag() + "已经发出····");
    }
}
