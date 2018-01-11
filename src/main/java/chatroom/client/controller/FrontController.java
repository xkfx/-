package chatroom.client.controller;

import chatroom.client.model.ClientMessageService;
import chatroom.client.model.UIManager;
import chatroom.client.ui.component.UserFrame;
import chatroom.common.message.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static chatroom.client.ui.enums.ButtonEnum.SEND;
import static chatroom.common.message.Iconst.PERSONAL_MESSAGE;
import static chatroom.common.message.Iconst.PUBLIC_MESSAGE;

/**
 * 监听用户动作，转换成模型更新。
 */
public class FrontController implements ActionListener {

    private ClientMessageService clientMessageService;

    private UIManager uiManager;

    private UserFrame userFrame;

    /**
     * 持有界面管理器的同时，注册相应对象的监听
     * @param manager
     */
    public void setUiManager(UIManager manager) {
        uiManager = manager;
        userFrame = uiManager.getUserFrame();
        // 便于 uiManager 动态注册事件监听
        uiManager.setFrontController(this);
    }

    public void setClientMessageService(ClientMessageService clientMessageService) {
        this.clientMessageService = clientMessageService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println(e);
        if (e.getActionCommand().equals(SEND.getExpression())) {
            sendMessage();
        }
    }

    private void sendMessage() {
        UserFrame userFrame = uiManager.getUserFrame();
        Long source = uiManager.getSource();
        Long target = userFrame.getTarget();
        String content = userFrame.getInput();


        if (target == null) {
            // 群发
            Message publicMsg = new Message(PUBLIC_MESSAGE, content);
            try {
                clientMessageService.send(publicMsg);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // 发送给好友
            Message message = new Message(PERSONAL_MESSAGE, content);
            message.setSource(source);
            message.setTarget(target);
            try {
                clientMessageService.send(message);
//                userFrame.displayMessage("你悄悄对" + userFrame.getTargetName()
//
//
//              + "
// 说：" + content + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
