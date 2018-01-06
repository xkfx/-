package chatroom.server.model.impl;

import chatroom.common.Message;
import chatroom.server.dao.UserDAO;
import chatroom.server.dao.impl.UserDAOImpl;
import chatroom.server.dto.Login;
import chatroom.server.dto.Register;
import chatroom.server.entity.User;
import chatroom.server.model.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

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
    public Message login(Login login) {
        String username = login.getUsername();
        String password = login.getPassword();

        User user = userDAO.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return Message.ok("");
        }
        return Message.fail("用户名或者密码错误");
    }

    @Override
    public Message logout() {
        return null;
    }

    @Override
    public void sendPublicMessage() {

    }
}
