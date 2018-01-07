package chatroom.server.model;

import chatroom.common.message.Message;
import chatroom.common.entity.Visitor;

import java.io.IOException;
import java.net.Socket;

/**
 * 提供消息群发和转发服务。
 * 维护一个接收者列表，“接收者”是指同意接收群发消息的用户。
 */
public interface MessageService {
    /**
     * 添加单个接收者
     * @param socket 接收者的 socket
     */
    void addAcceptor(Socket socket, Visitor visitor);

    /**
     * 删除单个接收者
     * @param socket 要删去的接收者的 socket
     */
    void deleteAcceptor(Socket socket);

    /**
     * 群发消息
     * @param socket 发送人的 socket
     * @param message
     * @throws IOException
     */
    void sendAll(Socket socket, Message message) throws IOException;

    /**
     * 单发消息。
     * 向目标 socket 写 Message 对象，确保接收方的 objectInputStream 已经打开。
     * @param socket 目标 socket
     * @param message 要发送的 Message 类对象
     * @throws IOException
     */
    void send(Socket socket, Message message) throws IOException;

    void closeOutputStream(Socket socket);
}
