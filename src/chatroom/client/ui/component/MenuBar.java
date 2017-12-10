package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    private JButton buttonTest = new JButton("☺");
    private JButton buttonTest2 = new JButton("☺");

    public MenuBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(buttonTest);
        add(buttonTest2);
    }
}
