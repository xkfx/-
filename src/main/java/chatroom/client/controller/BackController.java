package chatroom.client.controller;

import chatroom.client.util.ClientMessageService;
import chatroom.client.ui.UIManager;
import chatroom.client.ui.component.impl.UserFrame;
import chatroom.common.entity.User;
import chatroom.common.message.Message;
import chatroom.common.message.MsgFriends;
import chatroom.common.message.MsgProfile;

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

    private void publicMessage(String str) {
        UserFrame userFrame = uiManager.getUserFrame();
        userFrame.displayMessage(str);
    }

    private void updateProfile(Message message) {
        MsgProfile msgProfile = (MsgProfile) message;
        User user = msgProfile.getUser();
        // 实际上是传递给前端控制器
        uiManager.setSource(user.getUserId());
    }

    private void initFriendList(Message message) {
        MsgFriends msgFriends = (MsgFriends) message;
        uiManager.initFriendList(msgFriends.getUser());
    }

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
                                publicMessage(message.getContent());
                            }

                            if (message.getType() == USER_PROFILE) {
                                updateProfile(message);
                            }

                            if (message.getType() == PERSONAL_MESSAGE) {
                                UserFrame userFrame = uiManager.getUserFrame();
                                userFrame.displayMessage(message.getContent());
                            }

                            if (message.getType() == FRIEND_LIST) {
                                initFriendList(message);
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
}
