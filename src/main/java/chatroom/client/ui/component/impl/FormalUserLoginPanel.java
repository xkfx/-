package chatroom.client.ui.component.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static chatroom.client.ui.enums.ButtonEnum.LOGIN;
import static chatroom.client.ui.enums.ButtonEnum.REGISTER;

public class FormalUserLoginPanel extends JPanel {

    private static final JLabel LABEL_USERNAME = new JLabel("用户名：", JLabel.CENTER);
    private static final JLabel LABEL_PASSWORD = new JLabel("密码：", JLabel.CENTER);

    private final JTextField textUsername = new JTextField();
    private final JPasswordField textPassword = new JPasswordField();
    private final JButton buttonLogin = new JButton(LOGIN.getExpression());
    private final JButton buttonRegister = new JButton(REGISTER.getExpression());

    public FormalUserLoginPanel() {
        LABEL_USERNAME.setFont(new Font("楷体", 0, 20));
        LABEL_PASSWORD.setFont(new Font("楷体", 0, 20));
        textUsername.setFont(new Font("楷体", 0, 20));
        textPassword.setFont(new Font("楷体", 0, 20));
        buttonLogin.setFont(new Font("楷体", 0, 20));
        buttonRegister.setFont(new Font("楷体", 0, 20));
        buttonLogin.setFocusPainted(false);
        buttonRegister.setFocusPainted(false);

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(2, 2));
        panelInput.add(LABEL_USERNAME);
        panelInput.add(textUsername);
        panelInput.add(LABEL_PASSWORD);
        panelInput.add(textPassword);

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
