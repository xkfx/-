package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;

public class FormalUserLoginPanel extends JPanel {

    private JButton buttonLogin;
    private JButton buttonRegister;
    private JTextField textUsername;
    private JPasswordField textPassword;

    public FormalUserLoginPanel() {
        // 创建组件
        JLabel labelUsername = new JLabel("用户名");
        JLabel labelPassword = new JLabel("密码");
        textUsername = new JTextField();
        textPassword = new JPasswordField();
        // 设置组件
        labelUsername.setFont(new Font("", 0, 36));
        labelPassword.setFont(new Font("", 0, 36));
        textUsername.setFont(new Font("", 0, 36));
        textPassword.setFont(new Font("", 0, 36));
        // 布局
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(2, 2));
        panelInput.add(labelUsername);
        panelInput.add(textUsername);
        panelInput.add(labelPassword);
        panelInput.add(textPassword);

        buttonLogin = new JButton("登陆");
        buttonRegister = new JButton("注册");
        buttonLogin.setFocusPainted(false);
        buttonRegister.setFocusPainted(false);
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonLogin);
        panelButton.add(buttonRegister);

        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
    }
}
