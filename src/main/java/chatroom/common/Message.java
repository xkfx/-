package chatroom.common;

import java.io.Serializable;

public class Message implements Serializable {
    private int type;
    private boolean flag;
    private String content;

    public Message() {

    }

    public Message(String content) {
        this.content = content;
    }

    public Message(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }
}
