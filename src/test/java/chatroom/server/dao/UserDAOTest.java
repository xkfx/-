package chatroom.server.dao;

import chatroom.server.dao.impl.UserDAOImpl;
import chatroom.common.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDAOTest {
    private UserDAO userDAO = new UserDAOImpl();

    private Long userId;
    private String username = "testGuy@123.com";
    private final String PASSWORD = "pass123";

    @Before
    public void saveUser() {
        username += ("" + Math.random()); // 防止由于异常残留的数据影响测试

        User user = new User();
        user.setUsername(username);
        user.setPassword(PASSWORD);
        userDAO.saveUser(user);
    }

    @Test
    public void getUser() {
        User user = userDAO.getUserByUsername(username);
        userId = user.getUserId();
        assertEquals(PASSWORD, user.getPassword());

        user = userDAO.getUserById(userId);
        assertEquals(PASSWORD, user.getPassword());

        userDAO.removeByUserId(userId);
    }

    @Test
    public void getFriendList() {
        List<User> userList = userDAO.getFriendList(1002L);
        for (User x : userList) {
            System.out.println(x);
        }
    }
}
