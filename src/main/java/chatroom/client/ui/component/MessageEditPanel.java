package chatroom.client.ui.component;

import chatroom.client.model.ClientMessageService;
import chatroom.client.ui.enums.ButtonEnum;
import chatroom.client.ui.exception.UserInputException;
import chatroom.client.model.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MessageEditPanel extends JPanel {

    private JButton buttonTest = new TextStyle();
    private JButton buttonTest2 = new JButton("☺");
    private JButton buttonTest3 = new JButton("消息记录");
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
        buttonClose.setFocusPainted(false);
        inputArea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(inputArea);
        setLayout(new BorderLayout());

        JPanel panFunction = new JPanel();
        panFunction.setLayout(new FlowLayout(FlowLayout.LEFT));
        panFunction.add(buttonTest);
        panFunction.add(buttonTest2);

        JPanel panRight = new JPanel();
        panRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panRight.add(buttonTest3);

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
        return "我是猪";
    }
}