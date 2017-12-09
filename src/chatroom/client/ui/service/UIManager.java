package chatroom.client.ui.service;

import chatroom.client.entity.Client;
import chatroom.client.ui.entity.MessageDisplayBox;

/**
 * 便于组件之间交互
 */
public class UIManager {

    private Client client;
    private MessageDisplayBox messageDisplayBox;

    public MessageDisplayBox getChatBox() {
        return messageDisplayBox;
    }

    public void setChatBox(MessageDisplayBox messageDisplayBox) {
        this.messageDisplayBox = messageDisplayBox;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
