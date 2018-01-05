package chatroom.server.dao.impl;

import chatroom.server.dao.UserDAO;
import chatroom.server.db.DBAccess;
import chatroom.server.entity.User;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            if (result.next()) {
                user = new User();
                user.setUserId(result.getLong(1));
                user.setUsername(result.getString(2));
                user.setPassword(result.getString(3));
                user.setNickname(result.getString(4));
                user.setOther(result.getString(5));
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
        return user;
    }
}
