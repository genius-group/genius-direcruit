package com.sfac.geniusdirecruit.modules.backstagesystem.service;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:30
 * 概要：
 * XXXXX
 */
public interface UserService {
    List<User> selectAllUser();

    ResultEntity<User> insertUser(User user);

    User getUserById(int userId);

    ResultEntity<User> editUser(User user);

    PageInfo<User> getUsersBySearchBean(SearchBean searchBean);

    ResultEntity<Object> deleteUserByUserId(Integer userId);

    HashMap<Object, String> loginIn(User user);

    void logout();
}
