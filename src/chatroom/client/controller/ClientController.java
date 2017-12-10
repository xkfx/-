package chatroom.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 客户端控制器，将用户动作映射成模型更新。
 */
public class ClientController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}
