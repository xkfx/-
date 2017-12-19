package chatroom.client.controller;

import chatroom.client.model.ClientMessageService;
import chatroom.client.model.UIManager;
import chatroom.client.ui.component.ChatroomFrame;
import chatroom.common.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static chatroom.client.ui.enums.ButtonEnum.SEND;
import static chatroom.common.Iconst.PUBLIC_MESSAGE;

public class ChatroomFrontController implements ActionListener {

    private ClientMessageService clientMessageService;

    private UIManager uiManager;

    private ChatroomFrame chatroomFrame;

    public void setUiManager(UIManager manager) {
        uiManager = manager;
        chatroomFrame = uiManager.getChatroomFrame();
        chatroomFrame.addActionListener(this);
    }

    public void setClientMessageService(ClientMessageService clientMessageService) {
        this.clientMessageService = clientMessageService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SEND.getExpression())) publicMessage();
    }

    private void publicMessage() {
        Message message = new Message(PUBLIC_MESSAGE, chatroomFrame.getText());
        try {
            clientMessageService.send(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
