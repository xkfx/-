package chatroom.server.service;

import chatroom.common.Message;
import chatroom.server.entity.Visitor;

import java.io.IOException;
import java.net.Socket;

public interface MessageService {
    /**
     * 添加群发消息的接收者
     * @param socket
     */
    void addPublicMessageAcceptor(Socket socket, Visitor visitor) throws IOException;

    /**
     * 根据 id 除去对应的接收者
     * @param socket
     */
    void deleteAcceptorById(Socket socket);

    /**
     * 消息群发服务，仅向在线用户发送。
     */
    void publicMessage(Message message) throws IOException;
}
