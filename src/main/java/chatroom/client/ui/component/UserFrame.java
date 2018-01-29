package chatroom.client.ui.component;

import java.awt.event.ActionListener;

/**
 * 定义一个用户窗口能获取什么数据、呈现什么数据
 * 给关键的组件统一注册事件监听
 */
public interface UserFrame {
    /**
     * 仿照 HttpServletRequest 的方法 // to fix...
     * @param parameterName
     */
    void getParameter(String parameterName);

    void addActionListener(ActionListener listener);
}
