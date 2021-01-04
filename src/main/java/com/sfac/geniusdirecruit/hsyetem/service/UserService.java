package com.sfac.geniusdirecruit.hsyetem.service;


import com.sfac.geniusdirecruit.hsyetem.entity.User;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;

public abstract class UserService {
    public abstract ResultEntity<User> insertUser(User user);
}
