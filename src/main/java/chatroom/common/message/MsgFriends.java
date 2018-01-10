package chatroom.common.message;

import chatroom.common.entity.User;

import java.util.List;

import static chatroom.common.message.Iconst.FRIEND_LIST;

public class MsgFriends extends Message {

    private List<User> users;

    public MsgFriends(List<User> users) {
        super(FRIEND_LIST, null);
        this.users = users;
        for (User x : users) {
            x.setPassword("");
        }
    }

    public List<User> getUser() {
        return users;
    }

    @Override
    public String toString() {
        return "MsgFriends{" +
                "users=" + users +
                '}';
    }
}
