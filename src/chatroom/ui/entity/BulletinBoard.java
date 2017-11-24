package chatroom.ui.entity;

import javax.swing.*;

public class BulletinBoard extends JPanel {
    private JTextArea announcement = new JTextArea(23, 23);

    public BulletinBoard() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(announcement);
    }
}
