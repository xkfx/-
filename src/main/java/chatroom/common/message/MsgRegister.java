package chatroom.common.message;

import static chatroom.common.message.Iconst.GENERAL_REGISTER;

public class MsgRegister extends Message {

    public MsgRegister(String username, String password) {
        super(GENERAL_REGISTER, null);
        put("username", username);
        put("password", password);
    }
}
