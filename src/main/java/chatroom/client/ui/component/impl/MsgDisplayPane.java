package chatroom.client.ui.component.impl;

import javax.swing.*;
import java.awt.*;

public class MsgDisplayPane extends JTextPane {

    public MsgDisplayPane() {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame w = new JFrame();
            w.setSize(500, 500);
            w.setLocationByPlatform(true);

            MsgDisplayPane pane = new MsgDisplayPane();
            w.add(pane);
            w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            w.setVisible(true);
        });
    }
}
