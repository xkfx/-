package chatroom.client.ui.component.impl;

import chatroom.client.ui.enums.ButtonEnum;
import chatroom.client.ui.enums.LabelEunm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VisitorLoginPanel extends JPanel {

    private static final JLabel LABEL_NAME = new JLabel(LabelEunm.NICKNAME.getExpression(), JLabel.CENTER);

    private final JTextField textNickname = new JTextField("逍遥の一生");
    private final JButton buttonConnection  = new JButton(ButtonEnum.CONNECT_TO_SERVER.getExpression());

    public VisitorLoginPanel() {
        LABEL_NAME.setFont(new Font("楷体", 0, 25));
        textNickname.setFont(new Font("", 0, 25));
        buttonConnection.setFocusPainted(false);
        buttonConnection.setFont(new Font("楷体", 0, 25));

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(1, 2));
        panelInput.add(LABEL_NAME);
        panelInput.add(textNickname);

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonConnection);

        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
    }

    public String getNickname() {
        return textNickname.getText();
    }

    public void addActionListener(ActionListener listener) {
        buttonConnection.addActionListener(listener);
    }
}
