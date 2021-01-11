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
	
    ResultEntity<User> login(User user);

    User selectUserByUserName(String userName);

    //判断注册用户名是否唯一
    boolean isUserExist(String userName);

    //新增注册后的用户
    void insertRegisterUser(User user);



    //判断注册用户是否存在，根据输入电话
    boolean selectUserByTel(String tel);



    //新增注册后的用户，那么就把注册的电话号码送入数据库
//    void insertRegisterUserTel(String tel);

>>>>>>> 76435fe964f6dd8d9fa40064120200a68350a466
}
