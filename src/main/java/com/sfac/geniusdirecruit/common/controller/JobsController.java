package com.sfac.geniusdirecruit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobsController {
    @RequestMapping("/jobsPage")
    public String jobsPage(ModelMap modelMap) {
        modelMap.put("template", "backstagesystem/jobs");
        return "common/managerIndex";
    }
}
