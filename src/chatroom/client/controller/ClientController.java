package chatroom.client.controller;

import chatroom.client.model.MessageService;
import chatroom.client.model.UIManager;
import chatroom.client.model.impl.MessageServiceImpl;
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
public class ClientController implements ActionListener {

    private MessageService messageService;

    private UIManager uiManager;

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        if (e.getActionCommand().equals(CONNECT_TO_SERVER.getExpression())) {
            visitorLogin();
        }
    }

    private void visitorLogin() {
        LoginFrame loginFrame = uiManager.getLoginFrame();
        String serverIp = loginFrame.getServerIp();
        int serverPort = loginFrame.getServerPort();
        String nickname = loginFrame.getNickname();

        // 发送消息
        Message message = new VisitorLogin(nickname);
        messageService = new MessageServiceImpl();
        Message result = null;
        try {
            messageService.establishConnection(serverIp, serverPort);
            result = messageService.send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 验证结果
        if (result.getFlag()) {
            // close
            // open - 延用原来的 socket 要保证服务端的健壮性 意味着还是用这个 messageService 发消息。
        } else {
            // ui manager do something with message
            // messageService disconnection
        }
    }
}
