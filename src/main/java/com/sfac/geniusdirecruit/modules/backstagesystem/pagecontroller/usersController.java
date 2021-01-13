package com.sfac.geniusdirecruit.modules.backstagesystem.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class usersController {
    /**
     * http://127.0.0.1:8080/users/userPage
     */
    @RequestMapping("/userPage")
    public String loginPage(ModelMap modelMap) {
        modelMap.put("template", "backstagesystem/users");
        return "common/managerIndex";
    }
}
