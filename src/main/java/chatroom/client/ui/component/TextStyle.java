package chatroom.client.ui.component;

import chatroom.client.ui.enums.ButtonEnum;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TextStyle extends JButton {

    public TextStyle() {
        setText(ButtonEnum.TEXT_STYLE.getExpression());
        setFocusable(false);
    }
}
