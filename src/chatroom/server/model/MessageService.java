package chatroom.server.model;

import chatroom.common.Message;
import chatroom.server.entity.Visitor;

import java.io.IOException;
import java.net.Socket;

public interface MessageService {
    /**
     * 添加群发消息的接收者
     * @param socket
     */
    void addPublicMessageAcceptor(Socket socket, Visitor visitor);

    /**
     * 根据键除去对应的接收者
     * @param socket
     */
    void deleteAcceptorBySocket(Socket socket);

    /**
     * 消息群发服务，仅向在线用户发送。
     */
    void publicMessage(Message message) throws IOException;

    /**
     * 向单个用户发送消息
     * @param socket
     * @param message
     */
    void send(Socket socket, Message message) throws IOException;
}
