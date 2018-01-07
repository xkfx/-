package chatroom.common.message;

import chatroom.common.entity.User;

import static chatroom.common.message.Iconst.USER_PROFILE;

public class MsgProfile extends Message {

    private User user;

    public MsgProfile(User user) {
        super(USER_PROFILE, null);
        this.user = user;
        user.setPassword("");
    }

    public User getUser() {
        return user;
    }
}
