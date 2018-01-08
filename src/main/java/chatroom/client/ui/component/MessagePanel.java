package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;

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
}
