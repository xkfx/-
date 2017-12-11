package chatroom.client.ui.component;

import chatroom.client.model.MessageService;
import chatroom.client.model.impl.MessageServiceImpl;
import chatroom.client.model.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

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
        setTitle("chatroom v1.0");
        setSize(1360, 828);
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
        mainPanel.add(rightColumn, BorderLayout.EAST);

        JPanel menuBar = new chatroom.client.ui.component.MenuBar();

        // 布局
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // 让每个组件持有组件管理器
        UIManager = new UIManager();
        UIManager.setChatBox(messageDisplayPanel);
        messageEditPanel.setUIManager(UIManager);

        // 让 messageService 持有组件控制器，以便响应服务器的请求： server --> messageService --> GUI
        MessageService messageService = new MessageServiceImpl(UIManager);
        // 持有组件控制器的组件都可以调用 messageService 的方法： GUI --> messageService --> server
        UIManager.setMessageService(messageService);
        try {
            messageService.establishConnection("localhost", 10001);
            System.out.println("与服务器的连接建立");
            // 创建一个线程专门用于响应服务器的请求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            messageService.doResponse();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            messageService.shutdown();
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

    public void addActionListener(ActionListener listener) {
        messageEditPanel.addActionListener(listener);
    }
}
