package chatroom.client.ui.component.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MessagePanel extends JPanel {

    private MessageDisplayPanel messageDisplayPanel;

    private MessageEditPanel messageEditPanel;

    public MessagePanel(int rows, int columns) {
        messageDisplayPanel = new MessageDisplayPanel(rows, columns);
        messageEditPanel = new MessageEditPanel(columns);

        setLayout(new BorderLayout());
        add(messageDisplayPanel, BorderLayout.CENTER);
        add(messageEditPanel, BorderLayout.SOUTH);
    }

    public String getContent() {
        return messageEditPanel.getText();
    }

    public void addActionListener(ActionListener listener) {
        messageEditPanel.addActionListener(listener);
    }
}
