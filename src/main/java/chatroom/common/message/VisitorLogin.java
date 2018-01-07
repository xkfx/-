package chatroom.common.message;

import static chatroom.common.message.Iconst.VISITOR_ACCESS;

public class VisitorLogin extends Message {

    public VisitorLogin(String nickname) {
        super(VISITOR_ACCESS, nickname);
    }

    public String getNickname() {
        return getContent();
    }
}
