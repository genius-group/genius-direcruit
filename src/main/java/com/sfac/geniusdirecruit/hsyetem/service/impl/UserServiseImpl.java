package com.sfac.geniusdirecruit.hsyetem.service.impl;

import com.sfac.geniusdirecruit.hsyetem.dao.UserDao;
import com.sfac.geniusdirecruit.hsyetem.entity.User;
import com.sfac.geniusdirecruit.hsyetem.service.UserService;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
    public class UserServiseImpl extends UserService {

        @Autowired
        private UserDao userDao;

        @Override
        @Transactional
        public ResultEntity<User> insertUser(User user) {
            userDao.insertCategory(user);
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "Insert success", user);
        }
    }
