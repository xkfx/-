package chatroom.client.handler;

import chatroom.client.ui.UserInterface;
import chatroom.client.util.ClientMessageService;
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
 * LoginFrameHandler：发送验证消息，管理 GUI
 */
public class LoginFrameHandler implements ActionListener {

    private ClientMessageService clientMessageService;
    private UserInterface userInterface;

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
        UserFrameHandler userFrameHandler = new UserFrameHandler();
        userInterface.setUserFrameHandler(userFrameHandler);

        UserFrame userFrame = userInterface.getUserFrame();
        userFrame.setVisible(true);

        BackEndHandler backEndHandler = new BackEndHandler();
        backEndHandler.setUserInterface(userInterface);
        backEndHandler.setClientMessageService(clientMessageService);
        backEndHandler.startup(clientMessageService.getSocket());

        userFrameHandler.setUserInterface(userInterface);
        userFrameHandler.setClientMessageService(clientMessageService);
    }

    private void login() {
        LoginFrame loginFrame = userInterface.getLoginFrame();

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
            userInterface.displayWarning(result.getContent());
        }
    }

    private void register() {
        LoginFrame loginFrame = userInterface.getLoginFrame();

        String serverIp = loginFrame.getServerIp();
        int serverPort = loginFrame.getServerPort();
        String username = loginFrame.getUsername();
        String password = loginFrame.getPassword();
        System.out.println("正在解析用户信息······");

        // 发送消息
        Message message = new MsgRegister(username, password);
        Message result = sendFirstMsg(serverIp, serverPort, message);

        userInterface.displayWarning(result.getContent());
    }

    private void visitorLogin() {
        LoginFrame loginFrame = userInterface.getLoginFrame();
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

    public void setUserInterface(UserInterface manager) {
        userInterface = manager;
        LoginFrame loginFrame = userInterface.getLoginFrame();
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
