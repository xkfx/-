package chatroom.common;

import static chatroom.common.Iconst.GENERAL_REGISTER;

public class MsgRegister extends Message {

    public MsgRegister(String username, String password) {
        super(GENERAL_REGISTER, null);
        put("username", username);
        put("password", password);
    }
}
