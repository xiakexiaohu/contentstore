package com.tl.dao;

import com.tl.pojo.User;

public interface UserDao {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //用户登录
    User login(String username, String password);
}
