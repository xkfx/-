package chatroom.client.controller;

import chatroom.client.model.ClientMessageService;
import chatroom.client.model.UIManager;
import chatroom.client.ui.component.ChatroomFrame;
import chatroom.client.ui.component.LoginFrame;
import chatroom.common.Message;
import chatroom.common.VisitorLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static chatroom.client.ui.enums.ButtonEnum.CONNECT_TO_SERVER;

/**
 * 客户端控制器，将用户动作映射成对模型的调用。
 * 负责解析用户动作，调用 model ，包括主要的业务逻辑。
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
        if (e.getActionCommand().equals(CONNECT_TO_SERVER.getExpression())) visitorLogin();
    }

    private void visitorLogin() {
        LoginFrame loginFrame = uiManager.getLoginFrame();
        String serverIp = loginFrame.getServerIp();
        int serverPort = loginFrame.getServerPort();
        String nickname = loginFrame.getNickname();

        // 发送消息
        Message message = new VisitorLogin(nickname);
        clientMessageService = new ClientMessageService();
        Message result = null;
        try {
            clientMessageService.establishConnection(serverIp, serverPort);
            result = clientMessageService.send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result.getFlag()) {
            loginFrame.dispose();
            ChatroomFrame chatroomFrame = uiManager.getChatroomFrame();
            chatroomFrame.setVisible(true);

            ChatroomBackController backController = new ChatroomBackController();
            ChatroomFrontController frontController = new ChatroomFrontController();
            backController.setUiManager(uiManager);
            backController.startup(clientMessageService.getSocket());
            frontController.setUiManager(uiManager);
            frontController.setClientMessageService(clientMessageService);
        } else {
            // ui manager do something with message
            // clientMessageService disconnection
        }
    }
}
