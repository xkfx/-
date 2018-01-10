package chatroom.client.controller;

import chatroom.client.model.ClientMessageService;
import chatroom.client.model.UIManager;
import chatroom.client.ui.component.MessageFrame;
import chatroom.client.ui.component.UserFrame;
import chatroom.common.message.Message;
import chatroom.common.message.MsgFriends;
import chatroom.common.message.MsgProfile;
import chatroom.common.entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static chatroom.common.message.Iconst.*;

/**
 * 后台控制器，监听来自服务端的消息，转换成模型更新。
 */
public class BackController {

    private ClientMessageService clientMessageService;

    private UIManager uiManager;

    public void setClientMessageService(ClientMessageService clientMessageService) {
        this.clientMessageService = clientMessageService;
    }

    public void setUiManager(UIManager manager) {
        uiManager = manager;
    }

    public void startup(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectInputStream inputStream;
                try {
                    while (true) {
                        try {
                            inputStream = clientMessageService.getInputStream();
                            Object object = inputStream.readObject();
                            Message message = (Message) object;
                            if (message.getType() == PUBLIC_MESSAGE) {
                                System.out.println("我收到一条群发消息哦！！！");
                                publicMessage(message.getContent());
                            }
                            if (message.getType() == USER_PROFILE) {
                                updateProfile(message);
                            }
                            if (message.getType() == PERSONAL_MESSAGE) {
                                uiManager.displayWarning(message.toString());
                            }
                            if (message.getType() == FRIEND_LIST) {
                                MsgFriends msgFriends = (MsgFriends) message;
                                uiManager.displayWarning(msgFriends.toString());
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void publicMessage(String str) {
        UserFrame userFrame = uiManager.getUserFrame();
        userFrame.displayMessage(str);
    }

    private void updateProfile(Message message) {
        System.out.println("用户个人信息已经接收");

        MsgProfile msgProfile = (MsgProfile) message;
        User user = msgProfile.getUser();
        // 实际上是传递给前端控制器
        uiManager.setSource(user.getUserId());
        uiManager.displayWarning(user.toString());

        MessageFrame messageFrame = uiManager.getMessageFrame(1002L);
        messageFrame.setVisible(true);
    }
}
