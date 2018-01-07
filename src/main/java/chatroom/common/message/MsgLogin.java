package chatroom.common.message;

import static chatroom.common.message.Iconst.GENERAL_LOGIN;

public class MsgLogin extends Message {

    public MsgLogin(String username, String password) {
        super(GENERAL_LOGIN, null);
        put("username", username);
        put("password", password);
    }
}
