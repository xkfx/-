package chatroom.client;

import chatroom.client.handler.LoginFrameHandler;
import chatroom.client.ui.UserInterface;

public class Initializer {
    /**
     * 客户端程序的入口
     * @param args
     */
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.init();

        LoginFrameHandler loginFrameHandler = new LoginFrameHandler();
        loginFrameHandler.setUserInterface(userInterface);
    }
}
