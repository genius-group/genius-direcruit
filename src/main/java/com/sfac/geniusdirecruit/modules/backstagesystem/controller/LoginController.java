package com.sfac.geniusdirecruit.modules.backstagesystem.controller;

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {
@Autowired
private UserService userService;
    @RequestMapping("/login")
    public String index(){

        return "login";
    }
/*nujshdguihefuw*/
}
