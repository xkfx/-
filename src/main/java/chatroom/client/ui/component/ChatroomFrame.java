package chatroom.client.ui.component;

import chatroom.client.model.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 聊天室窗体
 */
public class ChatroomFrame extends JFrame {

    private UIManager UIManager;

    MessageDisplayPanel messageDisplayPanel;

    MessageEditPanel messageEditPanel;

    /**
     * 初始化窗体
     */
    public ChatroomFrame() {
        // 设置窗体
        setTitle("Little Pony v1.0");
        setSize(800, 700);
        setResizable(false);
        setLocationRelativeTo(null);

         // 配置组件
        messageDisplayPanel = new MessageDisplayPanel(13, 92);
        messageEditPanel = new MessageEditPanel();

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(messageDisplayPanel, BorderLayout.CENTER);
        chatPanel.add(messageEditPanel, BorderLayout.SOUTH);

        JPanel rightColumn = new RightColumn();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(chatPanel, BorderLayout.CENTER);
        // mainPanel.add(rightColumn, BorderLayout.EAST);

        JPanel menuBar = new chatroom.client.ui.component.MenuBar();

        // 布局
        setLayout(new BorderLayout());
        // add(menuBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void append(String str) {
        messageDisplayPanel.append(str);
    }

    public String getText() {
        return messageEditPanel.getText();
    }

    public void addActionListener(ActionListener listener) {
        messageEditPanel.addActionListener(listener);
    }
}
