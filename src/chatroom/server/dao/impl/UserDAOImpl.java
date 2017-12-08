package chatroom.server.dao.impl;

import chatroom.entity.Message;
import chatroom.server.dao.UserDAO;
import chatroom.server.entity.User;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        String password = "123456";
        User user = new User(username, password);
        return user;
    }
}
