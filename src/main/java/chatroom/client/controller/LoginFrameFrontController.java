package chatroom.client.controller;

import chatroom.client.model.ClientMessageService;
import chatroom.client.model.UIManager;
import chatroom.client.ui.component.UserFrame;
import chatroom.client.ui.component.LoginFrame;
import chatroom.common.message.Message;
import chatroom.common.message.MsgLogin;
import chatroom.common.message.MsgRegister;
import chatroom.common.message.VisitorLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static chatroom.client.ui.enums.ButtonEnum.CONNECT_TO_SERVER;
import static chatroom.client.ui.enums.ButtonEnum.LOGIN;
import static chatroom.client.ui.enums.ButtonEnum.REGISTER;

/**
 * 登陆界面的前端控制器，将用户动作映射成模型更新。
 */
public class LoginFrameFrontController implements ActionListener {

    private ClientMessageService clientMessageService;

    private UIManager uiManager;

    public void setUiManager(UIManager manager) {
        uiManager = manager;
        LoginFrame loginFrame = uiManager.getLoginFrame();
        loginFrame.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(CONNECT_TO_SERVER.getExpression())) visitorLogin();
        if (actionCommand.equals(LOGIN.getExpression())) login();
        if (actionCommand.equals(REGISTER.getExpression())) register();
    }

    private Message sendFirstMsg(String serverIp, int serverPort, Message firstMsg) {
        clientMessageService = new ClientMessageService();
        Message result = null;
        try {

            System.out.println("准备与服务器建立连接······");
            clientMessageService.establishConnection(serverIp, serverPort);

            result = clientMessageService.sendOnce(firstMsg);
            System.out.println("等待服务器响应······");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void initMainFrame() {
        System.out.println("正在打开窗口······");

        UserFrame userFrame = uiManager.getUserFrame();
        userFrame.setVisible(true);

        System.out.println("正在打开窗口2······");
        FrontController frontController = new FrontController();
        userFrame.append("前台控制器初始化完毕。\n");

        System.out.println("正在打开窗口3······");
        BackController backController = new BackController();
        userFrame.append("后台控制器初始化完毕。\n");
        backController.setUiManager(uiManager);
        backController.setClientMessageService(clientMessageService);
        backController.startup(clientMessageService.getSocket());
        userFrame.append("后台控制器启动完毕。\n");

        System.out.println("正在打开窗口4······");
        frontController.setUiManager(uiManager);
        frontController.setClientMessageService(clientMessageService);
    }

    private void login() {
        LoginFrame loginFrame = uiManager.getLoginFrame();

        String serverIp = loginFrame.getServerIp();
        int serverPort = loginFrame.getServerPort();
        String username = loginFrame.getUsername();
        String password = loginFrame.getPassword();
        System.out.println("正在解析用户信息······");

        // 发送消息
        Message message = new MsgLogin(username, password);
        Message result = sendFirstMsg(serverIp, serverPort, message);

        if (result.getFlag()) {
            loginFrame.dispose();
            initMainFrame();
        } else {
            uiManager.displayWarning(result.getContent());
        }
    }

    private void register() {
        LoginFrame loginFrame = uiManager.getLoginFrame();

        String serverIp = loginFrame.getServerIp();
        int serverPort = loginFrame.getServerPort();
        String username = loginFrame.getUsername();
        String password = loginFrame.getPassword();
        System.out.println("正在解析用户信息······");

        // 发送消息
        Message message = new MsgRegister(username, password);
        Message result = sendFirstMsg(serverIp, serverPort, message);

        uiManager.displayWarning(result.getContent());
    }

    private void visitorLogin() {
        LoginFrame loginFrame = uiManager.getLoginFrame();
        String serverIp = loginFrame.getServerIp();
        int serverPort = loginFrame.getServerPort();
        String nickname = loginFrame.getNickname();
        System.out.println("正在解析用户信息······");
        // 发送消息
        Message message = new VisitorLogin(nickname);
        Message result = sendFirstMsg(serverIp, serverPort, message);
        if (result.getFlag()) {
            loginFrame.dispose();
            initMainFrame();
        } else {

        }
    }
}
