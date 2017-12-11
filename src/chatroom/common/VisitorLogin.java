package chatroom.common;

import static chatroom.common.Iconst.VISITOR_ACCESS;

public class VisitorLogin extends Message {

    public VisitorLogin(String nickname) {
        super(VISITOR_ACCESS, nickname);
    }

    public String getNickname() {
        return getContent();
    }
}
