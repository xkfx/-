package chatroom.server.service;

import chatroom.common.Message;

import java.io.IOException;
import java.net.Socket;

/**
 * 消息服务，持有者是服务器而不是用户，服务器帮助用户转发消息，而不是用户通过 P2P 直接传。
 */
public interface MessageService {
    /**
     * 添加群发消息的接收者
     * @param socket
     */
    void addAcceptor(int id, Socket socket) throws IOException;

    /**
     * 根据 id 除去对应的接收者
     * @param id
     */
    void deleteAcceptorById(int id);

    /**
     * 消息群发服务，仅向在线用户发送。
     */
    void publicMessage(Message message) throws IOException;
}
