package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import com.sfac.geniusdirecruit.modules.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.common.entity.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:30
 * 概要：
 * XXXXX
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }


    @Override
    @Transactional
    public ResultEntity<User> insertUser(User user) {
        userDao.insertUser(user);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "insert success",user);

    }

    @Override
    public User getUserById(int userId) {
        return  userDao.getUserById(userId);
    }

    @Override
    public ResultEntity<User> editUser(User user) {
        userDao.editUser(user);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "update success",user);
    }

    @Override
    public PageInfo<User> getUsersBySearchBean(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(userDao.getUsersBySearchBean(searchBean))
                .orElse(Collections.emptyList()));
    }

    @Override
    public ResultEntity<Object> deleteUserByUserId(Integer userId) {
        userDao.deleteUserByUserId(userId);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Delete success");
    }

    @Override
    public HashMap<Object, String> loginIn(User user) {
        HashMap<Object,String> map = new HashMap<Object, String>();
        User user_db = userDao.selectUserByUserName(user.getUserName());

        System.err.println(user.getUserPwd());
        System.err.println(user_db.getUserPwd());
        if (user.getUserPwd().equals(user_db.getUserPwd())){
            System.err.println(11111);
            map.put("info","登录成功");
            return map;
        }else {
            return null;
        }

    }
}
