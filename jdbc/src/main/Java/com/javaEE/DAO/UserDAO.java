package com.javaEE.DAO;

import com.javaEE.Bean.User;

/**
 * Created by guoxingyu on 2018/5/21.
 */
public interface UserDAO {
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public User login(String userName, String password);

    /**
     * 注册
     * @param u
     */
    public void register(User u);

    /**
     * 检查用户名是否存在
     * @param userName
     * @return
     */
    public boolean check(String userName);
}
