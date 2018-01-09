package chatroom.client.ui.component;

import chatroom.client.model.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 聊天室窗体
 */
public class UserFrame extends JFrame {

    private UIManager UIManager;

    private MessageDisplayPanel messageDisplayPanel;

    private MessageEditPanel messageEditPanel;

    /**
     * 初始化窗体
     */
    public UserFrame() {
        // 设置窗体
        setTitle("SmallTalk");
        setSize(700, 750);
        setResizable(false);
        setLocationRelativeTo(null);

         // 配置组件
        messageDisplayPanel = new MessageDisplayPanel(13, 92);
        messageEditPanel = new MessageEditPanel();

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(messageDisplayPanel, BorderLayout.CENTER);
        chatPanel.add(messageEditPanel, BorderLayout.SOUTH);

        // 测试
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(chatPanel, BorderLayout.EAST);

        JPanel menuBar = new chatroom.client.ui.component.MenuBar();
        FriendPanel friendPanel = new FriendPanel();

        // 布局
        setLayout(new BorderLayout());
        // add(menuBar, BorderLayout.NORTH);
        add(friendPanel, BorderLayout.CENTER);
        // add(mainPanel, BorderLayout.EAST);
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
