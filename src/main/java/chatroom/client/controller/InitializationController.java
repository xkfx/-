package chatroom.client.controller;

import chatroom.client.model.UIManager;

public class InitializationController {

    public static void main(String[] args) {
        UIManager uiManager = new UIManager();
        uiManager.init();

        LoginFrameFrontController frontController = new LoginFrameFrontController();
        frontController.setUiManager(uiManager);
    }
}
