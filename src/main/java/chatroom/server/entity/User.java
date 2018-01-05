package chatroom.server.entity;

import java.util.List;

public class User {

    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private String other;

    private List<Long> friendId;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<Long> getFriendId() {
        return friendId;
    }

    public void setFriendId(List<Long> friendId) {
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
