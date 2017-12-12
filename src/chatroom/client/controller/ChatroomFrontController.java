package chatroom.client.controller;

import chatroom.client.model.ClientMessageService;
import chatroom.client.model.UIManager;
import chatroom.client.ui.component.ChatroomFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatroomFrontController implements ActionListener {

    private ClientMessageService clientMessageService;

    private UIManager uiManager;

    public void setUiManager(UIManager manager) {
        uiManager = manager;
        ChatroomFrame chatroomFrame = uiManager.getChatroomFrame();
        chatroomFrame.addActionListener(this);
    }

    public void setClientMessageService(ClientMessageService clientMessageService) {
        this.clientMessageService = clientMessageService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}
