package chatroom.server.service.impl;

import chatroom.entity.Message;
import chatroom.server.service.MessageService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static chatroom.entity.Iconst.PUBLIC_MESSAGE;

public class MessageServiceImpl implements MessageService {
    private Map<Integer, ObjectOutputStream> objectOutputStreamMap = new HashMap<>();

    @Override
    public void addAcceptor(int id, Socket socket) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStreamMap.put(id, os);
    }

    @Override
    public void deleteAcceptorById(int id) {
        objectOutputStreamMap.remove(id);
    }

    @Override
    public void publicMessage(Message message) throws IOException {
        if (message.getType() == PUBLIC_MESSAGE) {
            for (ObjectOutputStream os : objectOutputStreamMap.values()) {
                os.writeObject(message);
                os.flush();
            }

        }
    }
}
