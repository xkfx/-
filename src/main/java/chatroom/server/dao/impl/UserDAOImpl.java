package chatroom.server.dao.impl;

import chatroom.server.dao.UserDAO;
import chatroom.server.db.DBAccess;
import chatroom.common.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private DBAccess dbAccess = new DBAccess();

    @Override
    public boolean saveUser(User user) {
        String sql = "insert into user(username, password) values(?,?)";
        PreparedStatement prep = null;
        int i = 0;
        try {
            prep = dbAccess.getConnection().prepareStatement(sql);
            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            i = prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (i == 0) return false;
        return true;
    }

    @Override
    public boolean removeByUserId(Long userId) {
        String sql = "delete from user where user_id=?";
        PreparedStatement prep = null;
        int i = 0;
        try {
            prep = dbAccess.getConnection().prepareStatement(sql);
            prep.setLong(1, userId);
            i = prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (i == 0) return false;
        return true;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "select user_id, username, password, nickname, other from user where"
                + " username=?";
        PreparedStatement prep = null;
        ResultSet result = null;
        User user = null;
        try {
            prep = dbAccess.getConnection().prepareStatement(sql);
            prep.setString(1, username);
            result = prep.executeQuery();

            user = new User();
            setUserFields(result, user);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "select user_id, username, password, nickname, other from user where"
                + " user_id=?";
        PreparedStatement prep = null;
        ResultSet result = null;
        User user = null;
        try {
            prep = dbAccess.getConnection().prepareStatement(sql);
            prep.setLong(1, userId);
            result = prep.executeQuery();

            user = new User();
            setUserFields(result, user);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> getFriendList(Long userId) {
        List<User> users = null;
        List<Long> idList = new ArrayList<>();
        String sql = "select friend_id from friend_relation where"
                + " user_id=?";
        PreparedStatement prep = null;
        ResultSet resultId = null;
        try {
            prep = dbAccess.getConnection().prepareStatement(sql);
            prep.setLong(1, userId);
            resultId = prep.executeQuery();
            while (resultId.next()) {
                idList.add(resultId.getLong("friend_id"));
            }

            if (idList.size() > 0) {
                users = new ArrayList<>();
                for (Long friendId : idList) {
                    User user = getUserById(friendId);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    private void setUserFields(ResultSet result, User user) throws SQLException {
        if (result.next()) {
            user.setUserId(result.getLong("user_id"));
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
            user.setNickname(result.getString("nickname"));
            user.setOther(result.getString("other"));
        }
    }
}
