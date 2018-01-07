package chatroom.server.model.impl;

import chatroom.common.message.Message;
import chatroom.server.dao.UserDAO;
import chatroom.server.dao.impl.UserDAOImpl;
import chatroom.server.dto.Login;
import chatroom.server.dto.Register;
import chatroom.common.entity.User;
import chatroom.server.model.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();
    /**
     * 对外映射，根据 socket 找用户资料
     */
    private Map<Integer, User> socketUserMap = new HashMap<>();
    /**
     * 内部映射，查找好友
     */
    private Map<Long, User> longUserMap = new HashMap<>();

    public UserServiceImpl() {

    }

    @Override
    public Message register(Register reg) {
        if (userDAO.getUserByUsername(reg.getUsername()) == null) {
            User user = new User();
            user.setUsername(reg.getUsername());
            user.setPassword(reg.getPassword());
            if (userDAO.saveUser(user)) {
                return Message.ok("恭喜你，注册成功");
            } else {
                return Message.fail("服务器异常,注册失败");
            }
        } else {
            return Message.fail("该用户名已经被注册了哦~");
        }
    }

    @Override
    public Message login(int socketCode, Login login) {
        String username = login.getUsername();
        String password = login.getPassword();

        User user = userDAO.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            socketUserMap.put(socketCode, user);
            return Message.ok("");
        }
        return Message.fail("用户名或者密码错误");
    }

    @Override
    public User getUser(int socketCode) {
        return socketUserMap.get(socketCode);
    }

    @Override
    public Message logout(int socketCode) {
        socketUserMap.remove(socketCode);
        return null;
    }

    @Override
    public void sendPublicMessage() {

    }
}
