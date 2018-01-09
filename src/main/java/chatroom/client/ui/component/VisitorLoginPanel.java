package chatroom.client.ui.component;

import chatroom.client.ui.enums.ButtonEnum;
import chatroom.client.ui.enums.LabelEunm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VisitorLoginPanel extends JPanel {

    private JButton buttonLogin;
    private JTextField textNickname;

    public VisitorLoginPanel() {
        // 创建组件
        JLabel labelName = new JLabel(LabelEunm.NICKNAME.getExpression(), JLabel.CENTER);
        textNickname = new JTextField();
        // 设置组件
        textNickname.setText("逍遥の一生");
        labelName.setFont(new Font("楷体", 0, 25));
        textNickname.setFont(new Font("", 0, 25));
          // 布局
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(1, 2));
        panelInput.add(labelName);
        panelInput.add(textNickname);

        buttonLogin = new JButton(ButtonEnum.CONNECT_TO_SERVER.getExpression());
        buttonLogin.setFocusPainted(false);
        buttonLogin.setFont(new Font("楷体", 0, 25));
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonLogin);

        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
    }

    public String getNickname() {
        return textNickname.getText();
    }

    public void addActionListener(ActionListener listener) {
        buttonLogin.addActionListener(listener);
    }
}
