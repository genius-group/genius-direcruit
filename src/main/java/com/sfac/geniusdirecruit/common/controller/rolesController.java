package com.sfac.geniusdirecruit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class rolesController {
    /**
     * http://127.0.0.1:8080/roles/rolePage
     */
    @GetMapping("/roles/rolePage")
    public String loginPage(ModelMap modelMap) {
        modelMap.put("template", "backstagesystem/roles");
        return "common/managerIndex";
    }
}
