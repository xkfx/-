package chatroom.client.model;

import chatroom.client.controller.FrontController;
import chatroom.client.ui.component.impl.LoginFrame;
import chatroom.client.ui.component.impl.MessageFrame;
import chatroom.client.ui.component.impl.UserFrame;
import chatroom.common.entity.User;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理 GUI ，接受控制器的调用
 */
public class UIManager {
    /**
     * 本机客户端用户的 user_id
     * 便于 backController 向 frontController 传递用户id
     */
    private Long source;
    private FrontController frontController;
    private LoginFrame loginFrame;
    private UserFrame userFrame;

    /**
     * 当 frontController 在 setUIManager 的时候应该同时把自身引用传递给 uiManager
     * @param frontController
     */
    public void setFrontController(FrontController frontController) {
        this.frontController = frontController;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getSource() {
        return source;
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    /**
     * 创建一个登陆界面
     */
    public void init() {
        loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }

    /**
     * 创建或者获取主界面
     * @return
     */
    public UserFrame getUserFrame() {
        if (userFrame == null) {
            userFrame = new UserFrame();
            userFrame.addActionListener(frontController);
        }
        return userFrame;
    }

    /**
     * 初始化主界面的好友列表
     * @param users
     */
    public void initFriendList(List<User> users) {
        userFrame.initFriendList(users);
    }

    /**
     * 主要用于写 demo，弹出一个提示框
     * @param warning
     */
    public void displayWarning(String warning) {
        JOptionPane.showMessageDialog(null, warning);
    }

    // to-do line ============================================================

    private Map<Long, MessageFrame> targetPanelMap = new HashMap<>();

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
}
