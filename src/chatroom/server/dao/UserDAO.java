package chatroom.server.dao;

import chatroom.entity.Message;
import chatroom.server.entity.User;

public interface UserDAO {
    /**
     * 增加一条用户记录
     * @param user
     * @return
     */
    boolean saveUser(User user);

    /**
     * 通过用户名查找用户记录
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
