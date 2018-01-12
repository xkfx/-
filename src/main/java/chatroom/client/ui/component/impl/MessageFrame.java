package chatroom.client.ui.component.impl;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MessageFrame extends JFrame {

    private MessagePanel messagePanel;

    public MessageFrame() {
        messagePanel = new MessagePanel(40, 20);

        setSize(500, 600);
        add(messagePanel);
    }

    public String getContent() {
        return messagePanel.getContent();
    }

    public void addActionListener(ActionListener listener) {
        messagePanel.addActionListener(listener);
    }
}
