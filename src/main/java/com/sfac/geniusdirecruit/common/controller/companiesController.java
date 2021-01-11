package com.sfac.geniusdirecruit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class companiesController {
    /**
     * http://127.0.0.1:8080/roles/rolePage
     */
    @GetMapping("/companies/companyPage")
    public String loginPage(ModelMap modelMap) {
        modelMap.put("template", "backstagesystem/companies");
        return "common/managerIndex";
    }
}
