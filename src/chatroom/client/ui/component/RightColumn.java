package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;

public class RightColumn extends JPanel {
    private BulletinBoard bulletinBoard = new BulletinBoard();
    private UserList userList = new UserList();

    public RightColumn() {
        setLayout(new BorderLayout());
        add(bulletinBoard, BorderLayout.NORTH);
        add(userList, BorderLayout.CENTER);
    }
}
