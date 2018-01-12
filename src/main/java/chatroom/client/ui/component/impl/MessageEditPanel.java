package chatroom.client.ui.component.impl;

import chatroom.client.ui.enums.ButtonEnum;
import chatroom.common.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MessageEditPanel extends JPanel {

    private final JComboBox<User> friendBox = new JComboBox<>();
    private final JTextArea inputArea = new JTextArea(4, 22);
    private final JButton buttonSend = new JButton(ButtonEnum.SEND.getExpression());
    private final JButton buttonClose = new JButton(ButtonEnum.CLOESE.getExpression());

    public MessageEditPanel() {
        buttonSend.setFocusPainted(false);
        buttonSend.setFont(new Font("楷体", 0, 25));
        buttonClose.setFocusPainted(false);
        buttonClose.setEnabled(false);
        buttonClose.setFont(new Font("楷体", 0, 25));
        inputArea.setLineWrap(true);
        inputArea.setFont(new Font("楷体", 0, 25));
        friendBox.setFont(new Font("楷体", 0, 25));

        friendBox.addItem(null);

        JScrollPane panInput = new JScrollPane(inputArea);

        JPanel panMenu = new JPanel();
        panMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
        panMenu.add(friendBox);

        JPanel panButton = new JPanel();
        panButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panButton.add(buttonClose);
        panButton.add(buttonSend);

        setLayout(new BorderLayout());
        add(panMenu, BorderLayout.NORTH);
        add(panInput, BorderLayout.CENTER);
        add(panButton, BorderLayout.SOUTH);
    }

    public MessageEditPanel(int columns) {
        this();
        inputArea.setColumns(columns);
    }

    public void initFriendList(List<User> users) {
        for (User x : users) {
            friendBox.addItem(x);
        }
    }

    /**
     * 获取用户输入
     * @return
     */
    public String getText() {
        String text = inputArea.getText();
        if (text != null && !text.trim().equals("")) {
            inputArea.setText("");
            return text;
        } else {
            // throw new UserInputException("用户没有输入");
        }
        return "测试数据（默认发送）";
    }

    /**
     * 获得发送目标id
     * @return
     */
    public Long getTarget() {
        User user = (User) friendBox.getSelectedItem();
        if (user != null) {
            return user.getUserId();
        }
        return null;
    }

    public void addActionListener(ActionListener listener) {
        buttonSend.addActionListener(listener);
    }
}