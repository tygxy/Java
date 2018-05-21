# JAVA JDBC 

## 1.JDBC 使用步骤
- maven中配置mysql
```
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.32</version>
</dependency>
```
- 配置数据库的Properties，在src/main/resources/中添加DBconfig.Properties
```
url=jdbc:mysql://localhost:3306/javaEE
user=root
password=XXX
```
- 编写MySQLUtil工具类
```
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
```
- DAO设计模式完成开发


## 2.JDBC 知识点
- 分为两大类，分别是静态查询和动态查询，静态查询使用Statement，动态查询PreparedStatement
- 如果是增删改的SQL语句，使用executeUpdate(); 如果是查询的SQL语句，使用executeQuery(); 创建表的SQL语句使用execute()
- DAO设计模式包括三部分
	- Bean 与数据库中表结构相匹配的类
	- DAO 接口
	- DAO Imal 实现类
- conn.rollback(); 回滚，保证事务的原子性
- conn.setAutoCommit(false); 设置不自动提交

## 3.几个简单的Demo
- 无参数的查询
```java
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
 ```

- 有参数的插入数据
```java
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
```

- 有参数的查询，不设置自动提交
```
public void add() {
    String sql = "insert into student (id,name,gender,age) values (4,'guoxingyu','M',26)";
    MySQLUtil util = new MySQLUtil();
    Connection conn = util.openConnection();
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
    } finally {
        util.close(conn);
    }
}
```

- 创建表
```
public void createTable() {
    String sql = "create table account(id int, name varchar(20),age int)";
    MySQLUtil util = new MySQLUtil();
    Connection conn = util.openConnection();
    try {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);  // 创建表使用execute
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        util.close(conn);
    }
}
```



