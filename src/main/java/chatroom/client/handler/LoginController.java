package chatroom.client.handler;

import chatroom.client.util.ClientMessageService;
import chatroom.client.ui.UIManager;
import chatroom.client.ui.component.impl.LoginFrame;
import chatroom.client.ui.component.impl.UserFrame;
import chatroom.common.message.Message;
import chatroom.common.message.MsgLogin;
import chatroom.common.message.MsgRegister;
import chatroom.common.message.VisitorLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static chatroom.client.ui.enums.ButtonEnum.*;

/**
 * LoginController：发送验证消息，管理 GUI
 */
public class LoginController implements ActionListener {

    private ClientMessageService clientMessageService;
    private UIManager uiManager;

    private void printClientPrompt(final String prompt) {
        System.out.println(prompt);
    }

    private Message sendFirstMsg(String serverIp, int serverPort, Message firstMsg) {
        clientMessageService = new ClientMessageService();
        Message result = null;
        try {
            printClientPrompt("Ready to establish a connection with the server ...");
            clientMessageService.establishConnection(serverIp, serverPort);
            result = clientMessageService.sendOnce(firstMsg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void initMainFrame() {
        FrontController frontController = new FrontController();
        uiManager.setFrontController(frontController);

        UserFrame userFrame = uiManager.getUserFrame();
        userFrame.setVisible(true);

        BackController backController = new BackController();
        backController.setUiManager(uiManager);
        backController.setClientMessageService(clientMessageService);
        backController.startup(clientMessageService.getSocket());

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

    public void setUiManager(UIManager manager) {
        uiManager = manager;
        LoginFrame loginFrame = uiManager.getLoginFrame();
        loginFrame.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String command = e.getActionCommand();
        if (command.equals(CONNECT_TO_SERVER.getExpression())) visitorLogin();
        if (command.equals(LOGIN.getExpression())) login();
        if (command.equals(REGISTER.getExpression())) register();
    }
}
