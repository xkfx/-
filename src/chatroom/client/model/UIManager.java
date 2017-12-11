package chatroom.client.model;

import chatroom.client.controller.ClientController;
import chatroom.client.ui.component.LoginFrame;
import chatroom.client.ui.component.MessageDisplayPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 便于组件之间交互
 */
public class UIManager {

    private MessageService messageService;
    private MessageDisplayPanel messageDisplayPanel;

    public MessageDisplayPanel getChatBox() {
        return messageDisplayPanel;
    }

    public void setChatBox(MessageDisplayPanel messageDisplayPanel) {
        this.messageDisplayPanel = messageDisplayPanel;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    private LoginFrame loginFrame;

    private ClientController clientController;

    /**
     * 创建一个登陆界面
     */
    public void init(ClientController controller) {
        EventQueue.invokeLater(() -> {
            clientController = controller;
            loginFrame = new LoginFrame();
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setVisible(true);
            loginFrame.addActionListener(controller);
            controller.setUiManager(this);
        });
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }
}
