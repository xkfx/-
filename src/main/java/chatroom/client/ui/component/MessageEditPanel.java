package chatroom.client.ui.component;

import chatroom.client.model.ClientMessageService;
import chatroom.client.ui.enums.ButtonEnum;
import chatroom.client.ui.exception.UserInputException;
import chatroom.client.model.UIManager;
import chatroom.common.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class MessageEditPanel extends JPanel {

    private JComboBox<User> friendBox = new JComboBox<>();

    private JTextArea inputArea = new JTextArea(4, 22);
    private JButton buttonSend = new JButton(ButtonEnum.SEND.getExpression());
    private JButton buttonClose = new JButton(ButtonEnum.CLOESE.getExpression());

    private ClientMessageService clientMessageService;
    private UIManager UIManager;

    public MessageEditPanel(int columns) {
        this();
        inputArea.setColumns(columns);
    }

    public MessageEditPanel() {
        buttonSend.setFocusPainted(false);
        buttonSend.setFont(new Font("楷体", 0, 25));
        buttonClose.setFocusPainted(false);
        buttonClose.setEnabled(false);
        buttonClose.setFont(new Font("楷体", 0, 25));
        inputArea.setLineWrap(true);
        inputArea.setFont(new Font("楷体", 0, 25));
        JScrollPane jScrollPane = new JScrollPane(inputArea);
        setLayout(new BorderLayout());

        JPanel panFunction = new JPanel();
        panFunction.setLayout(new FlowLayout(FlowLayout.LEFT));
        panFunction.add(friendBox);

        friendBox.setFont(new Font("楷体", 0, 25));
        friendBox.addItem(null);

        JPanel panRight = new JPanel();
        panRight.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel panMenu = new JPanel();
        panMenu.setLayout(new GridLayout(1, 2));
        panMenu.add(panFunction);
        panMenu.add(panRight);

        JPanel panButton = new JPanel();
        panButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panButton.add(buttonClose);
        panButton.add(buttonSend);

        add(panMenu, BorderLayout.NORTH);
        add(jScrollPane, BorderLayout.CENTER);
        add(panButton, BorderLayout.SOUTH);
    }

    public void addActionListener(ActionListener listener) {
        buttonSend.addActionListener(listener);
    }

    public void setClientMessageService(ClientMessageService clientMessageService) {
        this.clientMessageService = clientMessageService;
    }

    public void setUIManager(UIManager UIManager) {
        this.UIManager = UIManager;
    }

    public void initFriendList(List<User> users) {
        for (User x : users) {
            friendBox.addItem(x);
        }
    }

    public String getTargetName() {
        User user = (User) friendBox.getSelectedItem();
        if (user != null) {
            return user.getNickname();
        }
        return null;
    }

    public Long getTarget() {
        User user = (User) friendBox.getSelectedItem();
        if (user != null) {
            return user.getUserId();
        }
        return null;
    }
    /**
     * 对用户的输入格式进行检查，用户输入未必总来自输入面板！
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
}