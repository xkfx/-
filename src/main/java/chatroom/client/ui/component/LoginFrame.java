package chatroom.client.ui.component;

import java.awt.event.ActionListener;

/**
 * 定义一个登陆窗口能获取什么数据、呈现什么数据
 * 给关键的组件统一注册事件监听
 */
public interface LoginFrame {

    void addActionListener(ActionListener listener);
}
