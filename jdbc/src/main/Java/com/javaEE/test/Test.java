package com.javaEE.test;

import com.javaEE.MySQLUtil.MySQLUtil;

import java.sql.*;

/**
 * Created by guoxingyu on 2018/5/20.
 */
public class Test {
    public static void main(String[] args) {
        MySQLUtil util = new MySQLUtil();
//        Connection conn = util.getConnection("jdbc:mysql://localhost:3306/spark","root","XXX");
        Connection conn = util.openConnection();


        Test test = new Test();

        try {
//            test.add(conn);
//            test.list(conn);
//            test.createTable(conn);
            test.query(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.close(conn);
        }
    }

    /**
     * 使用无参数的存储过程
     * @param conn
     */
    public void callableStatement(Connection conn) {
        String sql = "{call all_student()}";
        try {
            CallableStatement cstmt = conn.prepareCall(sql);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                System.out.println(id + ":" + name + ":" + gender + ":" + age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用有输入参数的存储过程
     * @param conn
     */
    public void callableStatement_add(Connection conn) {
        String sql = "{call insert_student(?,?,?,?)}";
        try {
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1,5);
            cstmt.setString(2,"guojianjun");
            cstmt.setString(3,"M");
            cstmt.setInt(4,51);
            cstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用prepareStatement动态查询
     * @param conn
     */
    public void query(Connection conn) {
        String sql = "select * from student where id = ? ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,3);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getString("gender"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加数据到数据库
     * @param conn
     */
    public void add(Connection conn) {
        String sql = "insert into student (id,name,gender,age) values (4,'guoxingyu','M',26)";
        try {
            conn.setAutoCommit(false); // 设置自动提交为否
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql); // 增删改使用executeUpdate
            conn.commit();  // 当自动提交为否时，要手动提交
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback(); // 回滚，保证事物的原子性
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 查询数据库内容
     * @param conn
     */
    public void list(Connection conn) {
        String sql = "select * from student";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql); // 查询使用executeQuery
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                System.out.println(id + " " + name + " " + gender + " " + age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     * @param conn
     */
    public void createTable(Connection conn) {
        String sql = "create table account(id int, name varchar(20),age int)";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);  // 创建表使用execute
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
