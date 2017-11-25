package chatroom.server.service;

import chatroom.entity.Message;

import java.io.IOException;
import java.net.Socket;

public interface MessageService {
    /**
     * 添加群发消息的接收者
     * @param socket
     */
    void addAcceptor(Socket socket) throws IOException;

    /**
     * 消息群发服务
     */
    void publicMessage(Message message) throws IOException;
}
