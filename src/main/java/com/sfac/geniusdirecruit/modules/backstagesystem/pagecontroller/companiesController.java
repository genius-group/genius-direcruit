package com.sfac.geniusdirecruit.modules.backstagesystem.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companines")
public class companiesController {
    /**
     * http://127.0.0.1:8080/roles/rolePage
     */
    @RequestMapping("/companyPage")
    public String loginPage(ModelMap modelMap) {
        modelMap.put("template", "backstagesystem/companies");
        return "common/managerIndex";
    }
}
