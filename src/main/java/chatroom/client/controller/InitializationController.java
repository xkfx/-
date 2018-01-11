package chatroom.client.controller;

import chatroom.client.model.UIManager;

public class InitializationController {
    /**
     * 客户端程序的入口
     * @param args
     */
    public static void main(String[] args) {
        UIManager uiManager = new UIManager();
        uiManager.init();

        LoginController loginController = new LoginController();
        loginController.setUiManager(uiManager);
    }
}
