package chatroom.ui.entity;

import javax.swing.*;
import java.awt.*;

public class RightBoard extends JPanel {
    private BulletinBoard bulletinBoard = new BulletinBoard();
    private UserList userList = new UserList();

    public RightBoard() {
        setLayout(new BorderLayout());
        add(bulletinBoard, BorderLayout.NORTH);
        add(userList, BorderLayout.CENTER);
    }
}
