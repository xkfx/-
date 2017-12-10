package chatroom.client.ui.component;

import chatroom.client.ui.enums.ButtonEnum;
import chatroom.client.ui.enums.LabelEunm;

import javax.swing.*;
import java.awt.*;

public class VisitorLoginPanel extends JPanel {

    private JButton buttonLogin;
    private JTextField textNick;

    public VisitorLoginPanel() {
        // 创建组件
        JLabel labelName = new JLabel(LabelEunm.NICKNAME.getExpression());
        textNick = new JTextField();
        // 设置组件
        textNick.setText("撒旦阿斯顿");
        labelName.setFont(new Font("", 0, 36));
        textNick.setFont(new Font("", 0, 36));
        // 布局
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(1, 2));
        panelInput.add(labelName);
        panelInput.add(textNick);

        buttonLogin = new JButton(ButtonEnum.CONNECT_TO_SERVER.getExpression());
        buttonLogin.setFocusPainted(false);
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonLogin);

        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
    }
}
