package chatroom.ui.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用于放置各种组件的聊天室主界面。
 * 中间放聊天窗，右边放用户列表，上面是登陆注册、个人中心状态栏，下面是补充信息栏。
 */
public class Chatroom extends JFrame implements ActionListener {
    private JPanel menuBox;
    private JTextArea chatBox;
    private JPanel messageBox;
    private JPanel userList;
    private JPanel rightBord;
    /**
     * 构造器
     */
    public Chatroom() {
        menuBox = new MenuBox();
        chatBox = new JTextArea(13, 92);
        JScrollPane scrollChatBox = new JScrollPane(chatBox);

        messageBox = new MessageBox();
        rightBord = new BulletinBoard();

        setTitle("chatroom v1.0");
        setSize(1360, 828);
        // setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(scrollChatBox, BorderLayout.CENTER);
        chatPanel.add(messageBox, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(chatPanel, BorderLayout.CENTER);
        mainPanel.add(rightBord, BorderLayout.EAST);

        add(menuBox, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}
