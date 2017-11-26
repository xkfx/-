package chatroom.ui.entity;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class BulletinBoard extends JPanel {
    private JTextArea announcement = new JTextArea(15, 23);

    public BulletinBoard() {
        add(announcement);
    }
}
