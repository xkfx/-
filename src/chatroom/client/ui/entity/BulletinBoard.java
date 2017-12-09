package chatroom.client.ui.entity;

import javax.swing.*;

public class BulletinBoard extends JPanel {
    private JTextArea announcement = new JTextArea(15, 23);

    public BulletinBoard() {
        announcement.setEditable(false);
        announcement.setText("大家好，我是一个即时聊天工具。");

        add(announcement);
    }
}
