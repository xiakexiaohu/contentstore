package com.tl.service;

import com.tl.pojo.User;

public interface UserService {
    User login(String username,String password);
}
