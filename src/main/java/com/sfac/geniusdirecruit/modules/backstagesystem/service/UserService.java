package com.sfac.geniusdirecruit.modules.backstagesystem.service;

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:30
 * 概要：
 * XXXXX
 */
public interface UserService {
    ResultEntity<User> selectUserByUserNameAndPwd(String userName, String userPwd);
}
