package chatroom.client.controller;

import chatroom.client.model.ClientMessageService;
import chatroom.client.model.UIManager;
import chatroom.client.ui.component.UserFrame;
import chatroom.common.message.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static chatroom.client.ui.enums.ButtonEnum.SEND;
import static chatroom.common.message.Iconst.PUBLIC_MESSAGE;

/**
 * 监听用户动作，转换成模型更新。
 */
public class FrontController implements ActionListener {

    private ClientMessageService clientMessageService;

    private UIManager uiManager;

    private UserFrame userFrame;

    public void setUiManager(UIManager manager) {
        uiManager = manager;
        userFrame = uiManager.getUserFrame();
        userFrame.addActionListener(this);
    }

    public void setClientMessageService(ClientMessageService clientMessageService) {
        this.clientMessageService = clientMessageService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        if (e.getActionCommand().equals(SEND.getExpression())) publicMessage();
    }

    private void publicMessage() {
        Message message = new Message(PUBLIC_MESSAGE, userFrame.getText());
        try {
            clientMessageService.send(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
