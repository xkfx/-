package chatroom.ui.entity;

import javax.swing.*;
import java.awt.*;

public class MenuBox extends JPanel {
    private JButton buttonTest = new JButton("☺");
    private JButton buttonTest2 = new JButton("☺");

    public MenuBox() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(buttonTest);
        add(buttonTest2);
    }
}
