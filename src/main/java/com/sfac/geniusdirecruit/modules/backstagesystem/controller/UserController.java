package com.sfac.geniusdirecruit.modules.backstagesystem.controller;

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:13
 * 概要：
 * XXXXX
 */
@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value ="/login",consumes = "application/json")
    ResultEntity<User> selectUserByUserNameAndPwd(User user){
        return userService.selectUserByUserNameAndPwd(user.getUserName(),user.getUserPwd());
    }
}
