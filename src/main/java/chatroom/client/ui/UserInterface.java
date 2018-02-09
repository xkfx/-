package chatroom.client.ui;

import chatroom.client.handler.UserFrameHandler;
import chatroom.client.ui.component.impl.LoginFrame;
import chatroom.client.ui.component.impl.MessageFrame;
import chatroom.client.ui.component.impl.UserFrame;
import chatroom.common.entity.User;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对整个用户界面的抽象
 */
public class UserInterface {
    /**
     * 本机客户端用户的 user_id
     * 便于 backController 向 userFrameHandler 传递用户id
     */
    private Long source;
    private UserFrameHandler userFrameHandler;
    private LoginFrame loginFrame;
    private UserFrame userFrame;

    /**
     * 当 userFrameHandler 在 setUIManager 的时候应该同时把自身引用传递给 uiManager
     * @param userFrameHandler
     */
    public void setUserFrameHandler(UserFrameHandler userFrameHandler) {
        this.userFrameHandler = userFrameHandler;
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
            userFrame.addActionListener(userFrameHandler);
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
            messageFrame.addActionListener(userFrameHandler);
            targetPanelMap.put(target, messageFrame);
        }
        return messageFrame;
    }
}
