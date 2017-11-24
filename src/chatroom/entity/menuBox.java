package chatroom.entity;

import javax.swing.*;
import java.awt.*;

public class menuBox extends JPanel {
    private JButton buttonTest = new JButton("☺");
    private JButton buttonTest2 = new JButton("☺");

    public menuBox() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(buttonTest);
        add(buttonTest2);
    }
}
