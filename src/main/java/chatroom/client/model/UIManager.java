package chatroom.client.model;

import chatroom.client.controller.FrontController;
import chatroom.client.ui.component.*;

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

    private Map<Long, MessageFrame> targetPanelMap = new HashMap<>();

    /**
     * 创建一个登陆界面
     */
    public void init() {
        loginFrame = new LoginFrame();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public UserFrame getUserFrame() {
        if (userFrame == null) {
            userFrame = new UserFrame();
            userFrame.addActionListener(frontController);
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

    public MessageFrame getMessageFrame(Long target) {
        MessageFrame messageFrame = null;
        if (targetPanelMap.get(target) != null) {
            messageFrame = targetPanelMap.get(target);
        } else {
            messageFrame = new MessageFrame();
            messageFrame.addActionListener(frontController);
            targetPanelMap.put(target, messageFrame);
        }
        return messageFrame;
    }

    public void displayWarning(String warning) {
        JOptionPane.showMessageDialog(null, warning);
    }
}
