package chatroom.ui.entity.frame;

import chatroom.client.entity.Client;
import chatroom.client.entity.impl.ClientImpl;
import chatroom.ui.entity.*;
import chatroom.ui.service.ComponentManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 聊天室窗体
 */
public class Chatroom extends JFrame {

    private ComponentManager componentManager;

    /**
     * 初始化窗体
     */
    public Chatroom() {
        // 设置窗体
        setTitle("chatroom v1.0");
        setSize(1360, 828);
        setResizable(false);
        setLocationRelativeTo(null);

         // 配置组件

        JTextArea MessageDisplayBox = new JTextArea(13, 92);
        MessageDisplayBox.setLineWrap(true);
        MessageDisplayBox.setEditable(false);

        JScrollPane scrollMessageDisplayBox = new JScrollPane(MessageDisplayBox);

        MessageEditBox messageEditBox = new MessageEditBox();

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(scrollMessageDisplayBox, BorderLayout.CENTER);
        chatPanel.add(messageEditBox, BorderLayout.SOUTH);

        JPanel rightColumn = new RightColumn();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(chatPanel, BorderLayout.CENTER);
        mainPanel.add(rightColumn, BorderLayout.EAST);

        JPanel menuBar = new chatroom.ui.entity.MenuBar();

        // 布局
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // 让每个组件持有组件管理器
        componentManager = new ComponentManager();
        componentManager.setChatBox(MessageDisplayBox);
        messageEditBox.setComponentManager(componentManager);

        // 让 client 持有组件控制器，以便响应服务器的请求： server --> client --> GUI
        Client client = new ClientImpl(componentManager);
        // 持有组件控制器的组件都可以调用 client 的方法： GUI --> client --> server
        componentManager.setClient(client);
        try {
            client.establishConnection("localhost", 10000);
            System.out.println("与服务器的连接建立");
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
