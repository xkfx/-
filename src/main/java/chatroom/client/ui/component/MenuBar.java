package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    private JButton buttonTest1 = new JButton("头像");
    private JButton buttonTest2 = new JButton("搜索框");
    private JButton buttonTest3 = new JButton("消息");
    private JButton buttonTest4 = new JButton("联系人");
    private JButton buttonTest5 = new JButton("云文件");
    private JButton buttonTest6 = new JButton("设置");
    private JButton buttonTest7 = new JButton("最小化");
    private JButton buttonTest8 = new JButton("最大化");
    private JButton buttonTest9 = new JButton("关闭");

    public MenuBar() {
        // 左
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(buttonTest1);
        leftPanel.add(buttonTest2);
        // 中
        JPanel centerPanel = new JPanel();
        centerPanel.add(buttonTest3);
        centerPanel.add(buttonTest4);
        centerPanel.add(buttonTest5);
        // 右
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightPanel.add(buttonTest6);
        rightPanel.add(buttonTest7);
        rightPanel.add(buttonTest8);
        rightPanel.add(buttonTest9);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}
