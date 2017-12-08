package chatroom.ui.entity;

import chatroom.ui.enums.ButtonEnum;

import javax.swing.*;
import java.awt.*;

public class TextStyle extends JButton {

    public TextStyle() {
        setText(ButtonEnum.TEXT_STYLE.getExpression());
        setFocusable(false);
    }
}
