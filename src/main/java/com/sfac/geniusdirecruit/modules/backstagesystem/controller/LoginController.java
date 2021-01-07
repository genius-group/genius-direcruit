package com.sfac.geniusdirecruit.modules.backstagesystem.controller;


import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {
    /**
     * 127.0.0.1/user/manager/login ---- get
     */
    @GetMapping("/manager/login")
    public String loginPage(ModelMap modelMap) {
        modelMap.put("template", "login");
        return "managerIndexSimple";
    }

    /**
     * 127.0.0.1/user/manager/register ---- get
     */
    @GetMapping("/manager/register")
    public String registerPage(ModelMap modelMap) {
        modelMap.put("template", "account/register");
        return "managerIndexSimple";
    }

}
