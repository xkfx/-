package chatroom.client.ui.entity;

import javax.swing.*;
import java.awt.*;

/**
 * 聊天面板，包括一个消息编辑窗和一个消息显示窗。
 */
public class ChatPanel extends JPanel {
    private MessageEditBox messageEditBox;
    private MessageDisplayBox messageDisplayBox;

    /**
     * @see MessageDisplayBox#MessageDisplayBox(int, int)
     * @param rows
     * @param columns
     */
    public ChatPanel(int rows, int columns) {
        messageEditBox = new MessageEditBox();
        messageDisplayBox = new MessageDisplayBox(rows, columns);
        setLayout(new BorderLayout());
        add(messageDisplayBox, BorderLayout.CENTER);
        add(messageEditBox, BorderLayout.SOUTH);
    }
}
