package chatroom.server.service.impl;

import chatroom.common.Message;
import chatroom.server.entity.Visitor;
import chatroom.server.service.MessageService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static chatroom.common.Iconst.PUBLIC_MESSAGE;

public class MessageServiceImpl implements MessageService {
    private Map<Socket, Visitor> socketVisitorMap = new HashMap<>();

    @Override
    public void addPublicMessageAcceptor(Socket socket, Visitor visitor) throws IOException {
        socketVisitorMap.put(socket, visitor);
    }

    @Override
    public void deleteAcceptorById(Socket socket) {
        socketVisitorMap.remove(socket);
    }

    @Override
    public void publicMessage(Message message) throws IOException {
        if (message.getType() == PUBLIC_MESSAGE) {
            for (Socket socket : socketVisitorMap.keySet()) {
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(message);
                outputStream.flush();
            }
        }
    }
}
