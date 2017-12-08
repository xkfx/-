package chatroom.ui.entity.frame;

import chatroom.client.entity.Client;
import chatroom.client.entity.impl.ClientImpl;
import chatroom.ui.enums.ButtonEnum;
import chatroom.ui.enums.LabelEunm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPanel extends JFrame implements ActionListener {
    private final String confirm = ButtonEnum.CONNECT_TO_SERVER.getExpression();
    private JButton button = new JButton(confirm);
    private JTextField textHost = new JTextField();
    private JTextField textNick = new JTextField();

    public LoginPanel() {
        setTitle("chatroom v1.0");
        setSize(450, 210);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel labelHost = new JLabel(LabelEunm.SERVER_HOST.getExpression());
        JLabel labelName = new JLabel(LabelEunm.NICKNAME.getExpression());

        button.setFocusPainted(false);
        textHost.setText("127.0.0.1");
        labelHost.setFont(new Font("", 0, 36));
        textHost.setFont(new Font("", 0, 36));
        labelName.setFont(new Font("", 0, 36));
        textNick.setFont(new Font("", 0, 36));

        JPanel panButton = new JPanel();
        panButton.setLayout(new FlowLayout());
        panButton.add(button);

        JPanel panInput = new JPanel();
        panInput.setLayout(new GridLayout(2, 2));
        panInput.add(labelHost);
        panInput.add(textHost);
        panInput.add(labelName);
        panInput.add(textNick);

        setLayout(new BorderLayout());
        add(panInput, BorderLayout.CENTER);
        add(panButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        LoginPanel loginPanel = new LoginPanel();
        loginPanel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(confirm)) {
            String hostName = textHost.getText().trim();
            String nickName = textNick.getText().trim();
            Client client = new ClientImpl();
            try {
                client.establishConnection(hostName, 10000);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "连接服务器失败！");
            }
        }
    }
}
