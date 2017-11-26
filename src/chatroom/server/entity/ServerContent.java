package chatroom.server.entity;

/**
 * 服务器上下文，存放诸如在线列表之类的。
 */
public interface ServerContent {

    void addOnlineUser();

    /**
     * 将用户从在线列表中删除，为防止恶意删除后期应该添加安全措施。
     */
    void offlineUserById();
}
