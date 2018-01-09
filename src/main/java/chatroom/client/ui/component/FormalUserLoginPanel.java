package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static chatroom.client.ui.enums.ButtonEnum.LOGIN;
import static chatroom.client.ui.enums.ButtonEnum.REGISTER;

public class FormalUserLoginPanel extends JPanel {

    private JButton buttonLogin;
    private JButton buttonRegister;
    private JTextField textUsername;
    private JPasswordField textPassword;

    public FormalUserLoginPanel() {
        // 创建组件
        JLabel labelUsername = new JLabel("邮箱：", JLabel.CENTER);
        JLabel labelPassword = new JLabel("密码：", JLabel.CENTER);
        textUsername = new JTextField();
        textPassword = new JPasswordField();
        // 设置组件
        labelUsername.setFont(new Font("楷体", 0, 20));
        labelPassword.setFont(new Font("楷体", 0, 20));
        textUsername.setFont(new Font("楷体", 0, 20));
        textPassword.setFont(new Font("楷体", 0, 20));
        // 布局
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(2, 2));
        panelInput.add(labelUsername);
        panelInput.add(textUsername);
        panelInput.add(labelPassword);
        panelInput.add(textPassword);

        buttonLogin = new JButton(LOGIN.getExpression());
        buttonLogin.setFont(new Font("楷体", 0, 20));
        buttonRegister = new JButton(REGISTER.getExpression());
        buttonRegister.setFont(new Font("楷体", 0, 20));
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

    public String getUsername() {
        return textUsername.getText();
    }

    public String getPassword() {
        return String.valueOf(textPassword.getPassword());
    }

    public void addActionListener(ActionListener listener) {
        buttonLogin.addActionListener(listener);
        buttonRegister.addActionListener(listener);
    }
}
