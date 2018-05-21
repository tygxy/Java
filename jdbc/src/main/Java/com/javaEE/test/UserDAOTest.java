package com.javaEE.test;

import com.javaEE.Bean.User;
import com.javaEE.DAOImpl.UserDaoImal;

/**
 * Created by guoxingyu on 2018/5/21.
 */
public class UserDAOTest {
    public static void main(String[] args) {
        UserDAOTest test = new UserDAOTest();
//        test.registerTest("guojianjun","123");
//        test.loginTest("guojianjun","123");
    }

    public void registerTest(String name, String password) {
        UserDaoImal userImal = new UserDaoImal();
        if (userImal.check(name)) {
            System.out.println("用户名已经存在");
        } else {
            User u = new User();
            u.setUserName(name);
            u.setPassword(password);

            userImal.register(u);
        }
    }

    public void loginTest(String name, String password) {
        UserDaoImal userImal = new UserDaoImal();
        if (userImal.check(name)) {
            User u = userImal.login(name,password);
            if (u != null) {
                System.out.println(u.getId());
                System.out.println(u.getUserName());
                System.out.println(u.getPassword());
            } else {
                System.out.println("密码不正确");
            }
        } else {
            System.out.println("用户名不存在");
        }
    }
}
