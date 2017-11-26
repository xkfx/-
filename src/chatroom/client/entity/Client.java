package chatroom.client.entity;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * 客户端接口，供 GUI 调用的顶层接口。
 */
public interface Client {
    /**
     * 与相应服务器建立连接
     * @param host
     * @param port
     */
    void establishConnection(String host, int port)
            throws UnknownHostException, IOException;

    /**
     * 负责接收，解析来自服务器的对象，并调用相应的服务，client 应该持有 GUI 的引用
     */
    void doResponse() throws IOException, ClassNotFoundException;

    /**
     * 群发消息
     * @param text
     */
    void sendPublicMessage(String text)
            throws IOException;

    /**
     * 断开连接
     */
    void shutdown() throws IOException;

    /**
     * 发出离线请求
     */
    void sendOfflineRequest();
}
