package chatroom.ui.entity;

import chatroom.ui.enums.ButtonEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageBox extends JPanel implements ActionListener {
    private JButton buttonTest = new JButton("☺");
    private JButton buttonTest2 = new JButton("☺");
    private JButton buttonTest3 = new JButton("消息记录");
    private JTextArea inputArea = new JTextArea(4, 92);
    private JButton buttonSend = new JButton(ButtonEnum.SEND.getExpression());
    private JButton buttonClose = new JButton(ButtonEnum.CLOESE.getExpression());

    MessageBox() {
        buttonSend.setFocusPainted(false);
        buttonClose.setFocusPainted(false);
        JScrollPane jScrollPane = new JScrollPane(inputArea);
        setLayout(new BorderLayout());

        JPanel panFunction = new JPanel();
        panFunction.setLayout(new FlowLayout(FlowLayout.LEFT));
        panFunction.add(buttonTest);
        panFunction.add(buttonTest2);

        JPanel panRight = new JPanel();
        panRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panRight.add(buttonTest3);

        JPanel panMenu = new JPanel();
        panMenu.setLayout(new GridLayout(1, 2));
        panMenu.add(panFunction);
        panMenu.add(panRight);

        JPanel panButton = new JPanel();
        panButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panButton.add(buttonClose);
        panButton.add(buttonSend);

        add(panMenu, BorderLayout.NORTH);
        add(jScrollPane, BorderLayout.CENTER);
        add(panButton, BorderLayout.SOUTH);

        buttonSend.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ButtonEnum.SEND.getExpression())) {
            System.out.println("用户点击了发送。");
            // 从发送缓存区 获取 检查 消息
            // client.send(Message)
            //
        }
    }
}