package com.javaEE.DAOImpl;

import com.javaEE.Bean.Meal;
import com.javaEE.Bean.MealInfo;
import com.javaEE.DAO.MealDAO;
import com.javaEE.MySQLUtil.MySQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoxingyu on 2018/5/21.
 */
public class MealDaoImal implements MealDAO{

    /**
     * 添加订单
     * @param m
     */
    public void add(Meal m) {
        String sql = "insert into MealTbl (createtime,userId,mealTypeId,num,comment) values (?,?,?,?,?) ";
        MySQLUtil util = new MySQLUtil();
        Connection conn = util.openConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,m.getCreateTime());
            pstmt.setInt(2,m.getUserId());
            pstmt.setInt(3,m.getMealType());
            pstmt.setInt(4,m.getNum());
            pstmt.setString(5,m.getComment());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(conn);
        }
    }

    /**
     * 输出所有订单
     * @return
     */
    public List listAllMeal() {
        String sql = "select " +
                        "m.id, " +
                        "m.createtime, " +
                        "u.name, " +
                        "mt.mealName, " +
                        "m.num, " +
                        "m.num * mt.price as total, " +
                        "m.comment " +
                    "from MealTbl m  " +
                    "left join  " +
                        "(select id,name " +
                        " from UserTbl) u  " +
                    "on u.id = m.userId " +
                    "left join " +
                        " MealTypeTbl mt " +
                     "on m.mealTypeId = mt.id";

        MySQLUtil util = new MySQLUtil();
        Connection conn = util.openConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<MealInfo> list = new ArrayList<MealInfo>();
            while (rs.next()) {
                MealInfo m = new MealInfo();
                m.setId(rs.getInt("id"));
                m.setCreatetime(rs.getString("createtime"));
                m.setUserName(rs.getString("name"));
                m.setMealName(rs.getString("mealName"));
                m.setNum(rs.getInt("num"));
                m.setTotal(rs.getDouble("total"));
                m.setComment(rs.getString("comment"));
                list.add(m);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(conn);
        }
        return null;
    }

    /**
     * 查询订单
     * @param id
     * @return
     */
    public MealInfo query(int id) {
        String sql = "select " +
                "m.id, " +
                "m.createtime, " +
                "u.name, " +
                "mt.mealName, " +
                "m.num, " +
                "m.num * mt.price as total, " +
                "m.comment " +
                "from MealTbl m  " +
                "left join  " +
                "(select id,name " +
                " from UserTbl) u  " +
                "on u.id = m.userId " +
                "left join " +
                " MealTypeTbl mt " +
                "on m.mealTypeId = mt.id " +
                "where m.id = ?";
        MySQLUtil util = new MySQLUtil();
        Connection conn = util.openConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                MealInfo m = new MealInfo();
                m.setId(rs.getInt("id"));
                m.setCreatetime(rs.getString("createtime"));
                m.setUserName(rs.getString("name"));
                m.setMealName(rs.getString("mealName"));
                m.setNum(rs.getInt("num"));
                m.setTotal(rs.getDouble("total"));
                m.setComment(rs.getString("comment"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(conn);
        }
        return null;
    }
}
