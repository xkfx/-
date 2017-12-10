package chatroom.client.model;

import chatroom.client.ui.component.MessageDisplayPanel;

/**
 * 便于组件之间交互
 */
public class UIManager {

    private Client client;
    private MessageDisplayPanel messageDisplayPanel;

    public MessageDisplayPanel getChatBox() {
        return messageDisplayPanel;
    }

    public void setChatBox(MessageDisplayPanel messageDisplayPanel) {
        this.messageDisplayPanel = messageDisplayPanel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
