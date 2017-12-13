package chatroom.client.controller;

import chatroom.client.model.ClientMessageService;
import chatroom.client.model.UIManager;
import chatroom.client.ui.component.ChatroomFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static chatroom.client.ui.enums.ButtonEnum.SEND;

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
        chatroomFrame.append(e.getActionCommand() + "\n");
        if (e.getActionCommand().equals(SEND.getExpression())) {
            chatroomFrame.append(chatroomFrame.getText() + "\n");
        }
    }
}
