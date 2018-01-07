package chatroom.common.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
    private int type;
    private boolean flag;
    private Long target;
    private String content;
    private Map<String, String> cookie = new HashMap<>();

    public Message() {

    }

    public Message(String content) {
        this.content = content;
    }

    public Message(boolean flag) {
        this.flag = flag;
    }

    public Message(boolean flag, String content) {
        this.flag = flag;
        this.content = content;
    }

    public static Message ok(String content) {
        return new Message(true, content);
    }

    public static Message fail(String content) {
        return new Message(false, content);
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

    public void put(String key, String value) {
        cookie.put(key, value); // 如果 key 有重复，后面的会把前面的替换掉
    }

    public String get(String key) {
        return cookie.get(key);
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }
}
