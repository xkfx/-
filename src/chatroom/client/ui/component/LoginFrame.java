package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginFrame extends JFrame {

    JTextField textServerIp;
    JTextField textServerPort;

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
        VisitorLoginPanel visitorLoginPanel = new VisitorLoginPanel();
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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setVisible(true);
        });
    }

}
