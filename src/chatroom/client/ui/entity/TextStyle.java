package chatroom.client.ui.entity;

import chatroom.client.ui.enums.ButtonEnum;

import javax.swing.*;

public class TextStyle extends JButton {

    public TextStyle() {
        setText(ButtonEnum.TEXT_STYLE.getExpression());
        setFocusable(false);
    }
}
