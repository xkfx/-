package chatroom.common;

import static chatroom.common.Iconst.GENERAL_LOGIN;

public class MsgLogin extends Message {

    public MsgLogin(String username, String password) {
        super(GENERAL_LOGIN, null);
        put("username", username);
        put("password", password);
    }
}
