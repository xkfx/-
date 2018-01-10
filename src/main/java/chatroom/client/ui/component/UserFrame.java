package chatroom.client.ui.component;

import chatroom.client.model.UIManager;
import chatroom.common.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

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
        setSize(750, 700);
        setResizable(false);
        setLocationRelativeTo(null);

         // 配置组件
        messageDisplayPanel = new MessageDisplayPanel(13, 92);
        messageEditPanel = new MessageEditPanel();

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(messageDisplayPanel, BorderLayout.CENTER);
        chatPanel.add(messageEditPanel, BorderLayout.SOUTH);

        // 布局
        setLayout(new BorderLayout());
        add(chatPanel, BorderLayout.CENTER);
    }

    public void displayMessage(String str) {
        messageDisplayPanel.append(str);
    }

    public void initFriendList(List<User> users) {
        messageEditPanel.initFriendList(users);
    }

    public String getTargetName() {
        return messageEditPanel.getTargetName();
    }

    public Long getTarget() {
        return messageEditPanel.getTarget();
    }

    public String getInput() {
        return messageEditPanel.getText();
    }

    public void addActionListener(ActionListener listener) {
        messageEditPanel.addActionListener(listener);
    }
}
