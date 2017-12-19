package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;

/**
 * 聊天面板，包括一个消息编辑窗和一个消息显示窗。
 */
public class ChatPanel extends JPanel {
    private MessageEditPanel messageEditPanel;
    private MessageDisplayPanel messageDisplayPanel;

    /**
     * @see MessageDisplayPanel#MessageDisplayPanel(int, int)
     * @param rows
     * @param columns
     */
    public ChatPanel(int rows, int columns) {
        messageEditPanel = new MessageEditPanel();
        messageDisplayPanel = new MessageDisplayPanel(rows, columns);
        setLayout(new BorderLayout());
        add(messageDisplayPanel, BorderLayout.CENTER);
        add(messageEditPanel, BorderLayout.SOUTH);
    }
}
