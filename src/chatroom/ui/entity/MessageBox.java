package chatroom.ui.entity;

import chatroom.client.entity.Client;
import chatroom.ui.enums.ButtonEnum;
import chatroom.ui.exception.UserInputException;
import chatroom.ui.service.ComponentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MessageBox extends JPanel implements ActionListener, KeyListener {

    private JButton buttonTest = new JButton("☺");
    private JButton buttonTest2 = new JButton("☺");
    private JButton buttonTest3 = new JButton("消息记录");
    private JTextArea inputArea = new JTextArea(4, 92);
    private JButton buttonSend = new JButton(ButtonEnum.SEND.getExpression());
    private JButton buttonClose = new JButton(ButtonEnum.CLOESE.getExpression());

    private Client client;
    private ComponentManager componentManager;

    public MessageBox() {
        buttonSend.setFocusPainted(false);
        buttonClose.setFocusPainted(false);
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

        buttonSend.addActionListener(this);
        inputArea.addKeyListener(this);
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    /**
     * 对用户的输入格式进行检查，用户输入未必总来自输入面板！
     * @return
     * @throws UserInputException
     */
    private String getInput() throws UserInputException {
        String input = inputArea.getText();
        if (input != null && !input.trim().equals("")) {
            inputArea.setText("");
            return input;
        } else {
            throw new UserInputException("用户没有输入");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ButtonEnum.SEND.getExpression())) {
            try {
                String input = getInput();
                componentManager.getClient().sendPublicMessage(input);
            } catch (UserInputException exception) {
                componentManager.getChatBox().append("您还没有输入哦！\n");
            } catch (Exception exception) {
                // 捕获发送中抛出的异常反馈给用户
                componentManager.getChatBox().append(exception.getMessage());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
       // System.out.println(e.getKeyChar() + e.getModifiers());
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        MessageBox messageBox = new MessageBox();
    }
}