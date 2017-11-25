package chatroom.ui.service;

import chatroom.client.entity.Client;

import javax.swing.*;

/**
 * 便于组件之间交互
 */
public class ComponentManager {
    private JTextArea chatBox;

    private Client client;

    public JTextArea getChatBox() {
        return chatBox;
    }

    public void setChatBox(JTextArea chatBox) {
        this.chatBox = chatBox;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
