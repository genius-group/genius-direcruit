package com.sfac.geniusdirecruit.modules.backstagesystem.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class usersController {
    /**
     * http://127.0.0.1:8080/users/userPage
     */
    @GetMapping("/users/userPage")
    public String loginPage(ModelMap modelMap) {
        modelMap.put("template", "backstagesystem/user/users");
        return "common/managerIndex";
    }
}
