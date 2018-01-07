package chatroom.server.dao;

import chatroom.common.entity.User;

public interface UserDAO {
    /**
     * 添加用户（用户注册）
     * @param user
     * @return 成功返回 true
     */
    boolean saveUser(User user);

    /**
     * 删除用户（测试用）
     * @param userId 用户id
     * @return 成功返回 true
     */
    boolean removeByUserId(Long userId);

    /**
     * 根据 username 查询用户对象（用户登陆）
     * @param username
     * @return 存在则返回该对象，否则返回 null
     */
    User getUserByUsername(String username);
}
