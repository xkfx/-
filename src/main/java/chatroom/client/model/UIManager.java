package chatroom.client.model;

import chatroom.client.controller.FrontController;
import chatroom.client.ui.component.MessagePanel;
import chatroom.client.ui.component.UserFrame;
import chatroom.client.ui.component.LoginFrame;
import chatroom.client.ui.component.MessageDisplayPanel;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 便于组件之间交互
 */
public class UIManager {
    /**
     * 便于动态创建窗口，并注册监听
     */
    private FrontController frontController;

    /**
     * 当 controller 在 setUIManager 的时候应该同时把自身引用传递给 uiManager
     * @param frontController
     */
    public void setFrontController(FrontController frontController) {
        this.frontController = frontController;
    }

    private LoginFrame loginFrame;

    private UserFrame userFrame;

    private Long source;

    private Map<Long, MessagePanel> targetPanelMap = new HashMap<>();

    /**
     * 创建一个登陆界面
     */
    public void init() {
        loginFrame = new LoginFrame();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        displayWarning("哎呦，不错哦~");
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public UserFrame getUserFrame() {
        if (userFrame == null) {
            userFrame = new UserFrame();
            userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        return userFrame;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getSource() {
        return source;
    }

    public MessagePanel getMessagePanel(Long target) {
        MessagePanel messagePanel = null;
        if (targetPanelMap.get(target) != null) {
            messagePanel = targetPanelMap.get(target);
        } else {
            messagePanel = new MessagePanel(30, 30);
            targetPanelMap.put(target, messagePanel);
        }
        return messagePanel;
    }

    private static boolean test = true;
    public void displayWarning(String warning) {
        if (test) {
            test = false;
        }
        JOptionPane.showMessageDialog(null, warning);
    }
}
