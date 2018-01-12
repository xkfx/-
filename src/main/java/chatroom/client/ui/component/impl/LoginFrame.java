package chatroom.client.ui.component.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private static final JLabel LABEL_IP = new JLabel("服务器IP：", JLabel.CENTER);
    private static final JLabel LABEL_PORT = new JLabel("端口号：", JLabel.CENTER);

    private final JTextField textServerIp = new JTextField("127.0.0.1");
    private final JTextField textServerPort = new JTextField("10000");
    private final VisitorLoginPanel visitorLoginPanel = new VisitorLoginPanel();
    private final FormalUserLoginPanel formalUserLoginPanel = new FormalUserLoginPanel();

    public LoginFrame() {
        setTitle("SmallTalk");
        setSize(540, 270);
        setResizable(false);
        setLocationRelativeTo(null);

        LABEL_IP.setFont(new Font("楷体", 0, 25));
        LABEL_PORT.setFont(new Font("楷体", 0, 25));
        textServerIp.setFont(new Font("楷体", 0, 25));
        textServerPort.setFont(new Font("", 0, 25));

        JPanel panelServerInfo = new JPanel();
        panelServerInfo.setLayout(new GridLayout(1, 4));
        panelServerInfo.add(LABEL_IP);
        panelServerInfo.add(textServerIp);
        panelServerInfo.add(LABEL_PORT);
        panelServerInfo.add(textServerPort);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.setFont(new Font("楷体", 0, 25));
        tabbedPane.add("游客", visitorLoginPanel);
        tabbedPane.add("用户登陆", formalUserLoginPanel);

        setLayout(new BorderLayout());
        add(panelServerInfo, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.SOUTH);
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
