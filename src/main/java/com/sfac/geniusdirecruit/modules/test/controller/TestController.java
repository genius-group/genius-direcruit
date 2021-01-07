package com.sfac.geniusdirecruit.modules.test.controller;

import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: yzs
 * @Date: 2020/12/30 22:21
 * 概要：
 * XXXXX
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserService userService;

    /**
     * http://localhost:8080/test/desc----get
     */
    @GetMapping("/desc")
    @ResponseBody
    public String testDesc(){
        return "This is a test!";
    }

    /**
     * http://localhost:8080/test/register----get
     */

    /*test12*/
    @RequestMapping("/register")
    public String testRegister(){
        return "frontdesk/register";
    }

    @RequestMapping("/login")
    public String testlogin(){
        return "frontdesk/login";
    }

    @PostMapping("/loginIn")
    @ResponseBody
    public HashMap<Object,String> loginIn(@RequestBody User user){
        HashMap<Object, String> map = userService.loginIn(user);
        System.err.println(map);
        return map;
    }

}
