package com.sfac.geniusdirecruit.modules.backstagesystem.service;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
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

    ResultEntity<User> insertUser(UserVo userVo,HttpServletRequest request);

    User getUserById(int userId);

    ResultEntity<User> editUser(User user);

    PageInfo<User> getUsersBySearchBean(SearchBean searchBean);

    ResultEntity<Object> deleteUserByUserId(Integer userId);

    HashMap<Object, String> loginIn(User user,HttpServletRequest request);

    void logout();
	
    ResultEntity<User> login(User user);

    User selectUserByUserName(String userName);


    HashMap<String, Object> sendCode(String email, HttpServletRequest request);

    //求职者注册
    HashMap<Object, String> registerStaff(User user);


    //发送短信
    HashMap<String, Object> sendSms(String phone, HttpServletRequest request);

    //手机短信进入
    HashMap<String, Object> smsEnter(UserVo userVo, HttpServletRequest request);

    HashMap<String, Object> sendMessage(String tel, HttpServletRequest request);

    HashMap<String, Object> emailLogin(String email, Integer code, HttpServletRequest request);

    HashMap<String, Object> messageLogin(String tel, Integer code, HttpServletRequest request);


    ResultEntity<User> insertUserByAdmin(User user);
}
