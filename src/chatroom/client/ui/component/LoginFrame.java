package chatroom.client.ui.component;

import chatroom.client.controller.ClientController;
import chatroom.client.model.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField textServerIp;
    private JTextField textServerPort;

    private VisitorLoginPanel visitorLoginPanel;

    public LoginFrame() {
        setTitle("Little Pony v1.0");
        setSize(430, 420);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel labelIp = new JLabel("服务器IP：", JLabel.CENTER);
        JLabel labelPort = new JLabel("端口号：", JLabel.CENTER);
        textServerIp = new JTextField("127.0.0.1");
        textServerPort = new JTextField("10001");

        JPanel panelServerInfo = new JPanel();
        panelServerInfo.setLayout(new GridLayout(1, 4));
        panelServerInfo.add(labelIp);
        panelServerInfo.add(textServerIp);
        panelServerInfo.add(labelPort);
        panelServerInfo.add(textServerPort);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        FormalUserLoginPanel formalUserLoginPanel = new FormalUserLoginPanel();
        visitorLoginPanel = new VisitorLoginPanel();
        tabbedPane.add("游客", visitorLoginPanel);
        tabbedPane.add("用户登陆", formalUserLoginPanel);

        JLabel labelLogo = new JLabel();
        // 临时 450 * 450 图片
        ImageIcon icon = new ImageIcon("out/production/chatroomfrom0/image/logo.jpg");
        labelLogo.setIcon(icon);

        setLayout(new BorderLayout());
        add(panelServerInfo, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.SOUTH);
        add(labelLogo, BorderLayout.CENTER);
    }

    public String getServerIp() {
        return textServerIp.getText();
    }

    public int getServerPort() {
        return Integer.parseInt(textServerPort.getText());
    }

    public String getNickname() {
        return visitorLoginPanel.getNickname();
    }

    public void addActionListener(ActionListener listener) {
        visitorLoginPanel.addActionListener(listener);
    }

    public static void main(String[] args) {
            // 创建客户端 UI 管理器
            UIManager uiManager = new UIManager();
            // 创建监听器
            ClientController controller = new ClientController();
            // 创建窗口 & 注册事件监听
            uiManager.init(controller);
            // 启动完毕
    }

}
