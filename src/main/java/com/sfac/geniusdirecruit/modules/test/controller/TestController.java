package com.sfac.geniusdirecruit.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yzs
 * @Date: 2020/12/30 22:21
 * 概要：
 * XXXXX
 */
@Controller
@RequestMapping("/test")
public class TestController {

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

}
