package chatroom.client.ui.component;

import chatroom.client.ui.enums.ButtonEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextStyle extends JButton {

    public TextStyle() {
        setText(ButtonEnum.TEXT_STYLE.getExpression());
        setFont(new Font("楷体", 0, 25));
        setFocusable(false);
    }
}
