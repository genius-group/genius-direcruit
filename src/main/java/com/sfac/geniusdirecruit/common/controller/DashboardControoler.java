package com.sfac.geniusdirecruit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description DashboardControoler
 * @Author huangheyi
 * @Date 2020/01/07 11:21
 */
@Controller
@RequestMapping("/common")
public class DashboardControoler {

    /**
     * 127.0.0.1/common/dashboard ---- get
     */
    @GetMapping("/dashboard")
    public String dashboardPage(ModelMap modelMap) {
        modelMap.put("template", "common/dashboard");
        return "managerIndex";
    }
}
