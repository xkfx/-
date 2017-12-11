package chatroom.common;

import java.io.Serializable;

public class Message implements Serializable {
    private int type;
    private boolean flag;
    private String content;

    public Message(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public boolean getFlag() {
        return flag;
    }
}
