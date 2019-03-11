package com.tl.service;

import com.tl.dao.UserDao;
import com.tl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: contentstore
 * @Description:
 * @Author: tuliang
 * @Date: 2019/2/22 4:40 PM
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * @param username
     * @param password
     * @return com.tl.pojo.User
     * @description 用户登录
     */
    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }
}
