package com.javaEE.MySQLUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by guoxingyu on 2018/5/21.
 */
public class MySQLUtil {
    /**
     * 通过参数设置读取Connection
     * @param url
     * @param user
     * @param password
     * @return
     */
    public Connection getConnection(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过配置文件读取Connection,配置文件保存在resources中
     * @return
     */
    public Connection openConnection() {
        Properties prop = new Properties();
        String url = null;
        String user = null;
        String password = null;

        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.Properties"));
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            return DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭Connection
     * @param conn
     */
    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
