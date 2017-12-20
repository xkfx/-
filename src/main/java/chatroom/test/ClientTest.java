package chatroom.test;

import chatroom.client.controller.LoginFrameFrontController;
import chatroom.client.ui.component.UserFrame;

import javax.swing.*;
import java.awt.*;

public class ClientTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // 初始化界面
            UserFrame userFrame = new UserFrame();
            userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            userFrame.setVisible(true);
            // 创建监听器
            LoginFrameFrontController controller = new LoginFrameFrontController();
            // 注册事件监听
            userFrame.addActionListener(controller);
            // 启动完毕
        });
    }
}
