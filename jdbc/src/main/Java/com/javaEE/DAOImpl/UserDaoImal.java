package com.javaEE.DAOImpl;

import com.javaEE.Bean.User;
import com.javaEE.DAO.UserDAO;
import com.javaEE.MySQLUtil.MySQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by guoxingyu on 2018/5/21.
 */
public class UserDaoImal implements UserDAO {
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public User login(String userName, String password) {
        String sql = " select id,name,password from UserTbl where name = ? and password = ?";
        MySQLUtil util = new MySQLUtil();
        Connection conn = util.openConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,userName);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");

                User u = new User();
                u.setId(id);
                u.setUserName(userName);
                u.setPassword(password);

                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(conn);
        }
        return null;
    }

    /**
     * 注册
     * @param u
     */
    public void register(User u) {
        String sql = " insert into UserTbl (name,password) values (?,?) ";
        MySQLUtil util = new MySQLUtil();
        Connection conn = util.openConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,u.getUserName());
            pstmt.setString(2,u.getPassword());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(conn);
        }
    }

    /**
     * 检查用户名是否存在
     * @param userName
     * @return
     */
    public boolean check(String userName) {
        String sql = " select id,name,password from UserTbl where name = ? ";
        MySQLUtil util = new MySQLUtil();
        Connection conn = util.openConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,userName);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(conn);
        }
        return false;
    }
}
