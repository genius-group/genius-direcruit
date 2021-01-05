package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:30
 * 概要：
 * XXXXX
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public ResultEntity<User> selectUserByUserNameAndPwd(String userName, String userPwd) {
        userDao.selectUserByUserNameAndPwd(userName,userPwd);
        return  new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status, "Category name is repeat.");
    }
}
