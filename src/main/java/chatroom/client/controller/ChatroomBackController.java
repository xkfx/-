package chatroom.client.controller;

import chatroom.client.model.UIManager;
import chatroom.client.ui.component.UserFrame;
import chatroom.common.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static chatroom.common.Iconst.PUBLIC_MESSAGE;

/**
 * 后台控制器，监听来自服务端的消息，转换成模型更新。
 */
public class ChatroomBackController {

    private UIManager uiManager;

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
                            inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                            Object object = inputStream.readObject();
                            Message message = (Message) object;
                            if (message.getType() == PUBLIC_MESSAGE) publicMessage(message.getContent());
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
        userFrame.append(str);
    }
}
