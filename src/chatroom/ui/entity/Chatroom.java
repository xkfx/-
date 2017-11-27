package chatroom.ui.entity;

import chatroom.client.entity.Client;
import chatroom.client.entity.impl.ClientImpl;
import chatroom.ui.service.ComponentManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 用于放置各种组件的聊天室主界面。
 * 中间放聊天窗，右边放用户列表，上面是登陆注册、个人中心状态栏，下面是补充信息栏。
 */
public class Chatroom extends JFrame {
    private JPanel menuBox;
    private JTextArea chatBox;
    private MessageBox messageBox;
    private JPanel userList;
    private JPanel rightBord;

    private ComponentManager componentManager;

    /**
     * 构造器，负责聊天室的初始化
     */
    public Chatroom() {
        menuBox = new MenuBox();
        chatBox = new JTextArea(13, 92);
        chatBox.setLineWrap(true);
        chatBox.setEditable(false);
        JScrollPane scrollChatBox = new JScrollPane(chatBox);
        messageBox = new MessageBox();
        rightBord = new RightBoard();

        setTitle("chatroom v1.0");
        setSize(1360, 828);
        setResizable(false);
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
        // UI 到此结束

        componentManager = new ComponentManager();
        componentManager.setChatBox(chatBox);
        messageBox.setComponentManager(componentManager);

        // 让 client 持有组件控制器，以便响应服务器的请求： server --> client --> GUI
        Client client = new ClientImpl(componentManager);
        // 持有组件控制器的组件都可以调用 client 的方法： GUI --> client --> server
        componentManager.setClient(client);
        try {
            client.establishConnection("localhost", 10000);
            // 创建一个线程专门用于响应服务器的请求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            client.doResponse();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            // 没正常关掉咋办？
                            client.shutdown();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            // 锁住所有可能抛出空指针异常的操作。
            System.out.println("建立连接失败====\n==========\n===========\n========\n===============");
            e.printStackTrace();
        }
    }
}
