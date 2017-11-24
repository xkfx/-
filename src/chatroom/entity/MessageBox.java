package chatroom.entity;

import javax.swing.*;
import java.awt.*;

public class MessageBox extends JPanel {
    private JButton buttonTest = new JButton("☺");
    private JButton buttonTest2 = new JButton("☺");
    private JButton buttonTest3 = new JButton("消息记录");
    private JTextArea inputArea = new JTextArea(4, 92);
    private JButton buttonSend = new JButton("发送");
    private JButton buttonClose = new JButton("关闭");

    MessageBox() {
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
    }
}