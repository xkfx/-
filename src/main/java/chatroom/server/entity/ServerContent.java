package chatroom.server.entity;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 相当于一个公告栏，采用观察者模式。服务器上下文是主体(subject),各种服务是观察者(observer)。
 */
public class ServerContent {
    /**
     * 统计在线人数
     */
    private static int onlineCount = 0;
    /**
     * 保存用户信息
     */
    private Map<Integer, User> userMap = new HashMap<>();

    public void addUser(int id, User user) {
        userMap.put(id, user);
    }

    public User getUserById(int id) {
        return userMap.get(id);
    }
}
