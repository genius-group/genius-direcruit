package com.sfac.geniusdirecruit.modules.backstagesystem.controller;

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:13
 * 概要：
 * XXXXX
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value ="/login",consumes = "application/json")
    ResultEntity<User> selectUserByUserNameAndPwd(@RequestBody User user){

        return userService.selectUserByUserNameAndPwd(user.getUserName(),user.getUserPwd());
    }
}
