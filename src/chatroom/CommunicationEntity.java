package chatroom;

/**
 * 通信实体接口，每个通信实体都需要有收、发、解析、响应消息的能力。
 */
public interface CommunicationEntity {
    /**
     * 接收消息
     */
    void getMessage();

    /**
     * 发送消息
     */
    void sendMessage();

    /**
     * 解析消息，对字节流（？）做处理。
     */
    void parseTheMessage();

    /**
     * 取决通信实体的类别、状态，以及消息的类型、内容
     */
    void doResponse();
}
