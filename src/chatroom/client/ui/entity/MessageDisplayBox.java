package chatroom.client.ui.entity;

import javax.swing.*;

/**
 * 消息显示窗，可以认为是 JTextArea 的代理类，
 * 具有和 JTextArea 一样的 append 方法。
 */
public class MessageDisplayBox extends JScrollPane {

    private JTextArea messageDisplayBox;

    /**
     * 创建一个空的消息显示窗，具有确定的行数和列数。
     * @param rows the number of rows >= 0
     * @param columns the number of columns >= 0
     */
    public MessageDisplayBox(int rows, int columns) {
        messageDisplayBox = new JTextArea(rows, columns);
        messageDisplayBox.setLineWrap(true);
        messageDisplayBox.setEditable(false);
        setViewportView(messageDisplayBox);
    }

    /**
     * 将所给的文本追加到文档的底部，如果字符串为空就啥也不做。
     * @param str the text to insert
     */
    public void append(String str) {
        messageDisplayBox.append(str);
    }
}
