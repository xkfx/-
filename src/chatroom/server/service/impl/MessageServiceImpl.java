package chatroom.server.service.impl;

import chatroom.entity.Message;
import chatroom.server.service.MessageService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import static chatroom.entity.Iconst.PUBLIC_MESSAGE;

public class MessageServiceImpl implements MessageService {
    private List<ObjectOutputStream> Acceptors = new LinkedList<>();

    @Override
    public void addAcceptor(Socket socket) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        Acceptors.add(os);
    }

    @Override
    public void publicMessage(Message message) throws IOException {
        if (message.getType() == PUBLIC_MESSAGE) {
            for (ObjectOutputStream x : Acceptors) {
                x.writeObject(message);
                x.flush();
            }
        }
    }
}
