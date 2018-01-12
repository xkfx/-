package chatroom.client.ui.component.impl;

import chatroom.common.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class UserFrame extends JFrame {

    private final MessageDisplayPanel messageDisplayPanel = new MessageDisplayPanel(13, 92);
    private final MessageEditPanel messageEditPanel = new MessageEditPanel();

    public UserFrame() {
        setTitle("SmallTalk");
        setSize(750, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(messageDisplayPanel, BorderLayout.CENTER);
        chatPanel.add(messageEditPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(chatPanel, BorderLayout.CENTER);
    }

    public Long getTarget() {
        return messageEditPanel.getTarget();
    }

    public String getInput() {
        return messageEditPanel.getText();
    }

    public void initFriendList(List<User> users) {
        messageEditPanel.initFriendList(users);
    }

    public void displayMessage(String str) {
        messageDisplayPanel.append(str);
    }

    public void addActionListener(ActionListener listener) {
        messageEditPanel.addActionListener(listener);
    }
}
