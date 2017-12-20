package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;

public class FriendPanel extends JPanel {

    public FriendPanel() {
        // 左
        JPanel friendList = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.NORTH);
        MessagePanel displayPanel_1 = new MessagePanel(25, 20);
        tabbedPane.add("好友", displayPanel_1);
        tabbedPane.add("群组", null);
        tabbedPane.add("多人聊天", null);
        friendList.add(tabbedPane);
        // 右
        JPanel friendInformation = new JPanel();
        MessagePanel displayPanel_2 = new MessagePanel(29, 45);
        friendInformation.add(displayPanel_2);

        setLayout(new BorderLayout());
        add(friendList, BorderLayout.WEST);
        add(friendInformation, BorderLayout.CENTER);
    }
}
