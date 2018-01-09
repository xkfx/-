package chatroom.client.ui.component;

import chatroom.client.controller.LoginFrameFrontController;
import chatroom.client.model.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField textServerIp;
    private JTextField textServerPort;

    private VisitorLoginPanel visitorLoginPanel;
    private FormalUserLoginPanel formalUserLoginPanel;

    public LoginFrame() {
        setTitle("SmallTalk");
        setSize(540, 270);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel labelIp = new JLabel("服务器IP：", JLabel.CENTER);
        labelIp.setFont(new Font("楷体", 0, 25));
        JLabel labelPort = new JLabel("端口号：", JLabel.CENTER);
        labelPort.setFont(new Font("楷体", 0, 25));
        textServerIp = new JTextField("127.0.0.1");
        textServerIp.setFont(new Font("楷体", 0, 25));
        textServerPort = new JTextField("10000");
        textServerPort.setFont(new Font("", 0, 25));

        JPanel panelServerInfo = new JPanel();
        panelServerInfo.setLayout(new GridLayout(1, 4));
        panelServerInfo.add(labelIp);
        panelServerInfo.add(textServerIp);
        panelServerInfo.add(labelPort);
        panelServerInfo.add(textServerPort);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.setFont(new Font("楷体", 0, 25));
        formalUserLoginPanel = new FormalUserLoginPanel();
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

    public String getUsername() {
        return formalUserLoginPanel.getUsername();
    }

    public String getPassword() {
        return formalUserLoginPanel.getPassword();
    }

    public void addActionListener(ActionListener listener) {
        visitorLoginPanel.addActionListener(listener);
        formalUserLoginPanel.addActionListener(listener);
    }
}
