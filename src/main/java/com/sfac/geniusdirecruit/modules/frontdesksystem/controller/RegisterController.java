package com.sfac.geniusdirecruit.modules.frontdesksystem.controller;
/*
 * @projectName: genius-direcruit
 * @documentName: RegisterController
 * @author: WJM
 * @date:2021/1/6 14:05
 */

import com.sfac.geniusdirecruit.common.utile.SmsSend;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Company;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Jobhunter;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.vo.UserVo;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Random;


@Controller
@RequestMapping("/frontdesk")
public class RegisterController {


    @Autowired
    UserService userService;


    @Autowired
    SmsSend smsSend;



    /**
     * 127.0.0.1:8080/frontdesk/register ---- get
     *
     * http://localhost:8080/frontdesk/register----get
     */

    @RequestMapping("/register")
    public String registerPage() {

        return "frontdesk/register";

    }

/*
*  127.0.0.1:8080/frontdesk/SubmissionPage
* */

    @RequestMapping("/SubmissionPage")
    public String submissionPage(@RequestParam String flag, ModelMap modelMap) {
        System.err.println(">>>>>>>>>>>>>SubmissionPage>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+flag);
        modelMap.addAttribute("flag",flag);
        return "frontdesk/registerSubmissionOne";

    }

    /*
     *  127.0.0.1:8080/frontdesk/SubmissionPageTwo
     * 求职者信息表
     * */

    @RequestMapping("/SubmissionPageTwo")
    public String goJobHunterPage() {

        return "frontdesk/registerSubmissionTwo";

    }

    /*
     *  127.0.0.1:8080/frontdesk/GoCompanyPage
     * 企业表
     * */

    @RequestMapping("/GoCompanyPage")
    public String goCompanyPage() {

        return "frontdesk/registerSubmissionCompany";

    }


    /*
     *  127.0.0.1:8080/frontdesk/GoIndex
     * */

    @RequestMapping("/GoIndex")
    public String goIndex() {

        return "frontdesk/index";

    }



    //企业Company表信息添加
    @PostMapping(value = "/SubmissionCompany",consumes = "application/json")
    @ResponseBody
    public HashMap<Object,String> SubmissionCompany(@RequestBody Company company, HttpServletRequest request){

        System.err.println("SubmissionCompany.........controller............."+company);

        HashMap<Object, String> map = userService.registerCompany(company,request);

        System.err.println("SubmissionCompany.........controller............."+map);

        return map;
    }





    //求职者Jobhunter表信息添加
    @PostMapping(value = "/SubmissionTwo",consumes = "application/json")
    @ResponseBody
    public HashMap<Object,String> SubmissionTwo(@RequestBody Jobhunter jobhunter, HttpServletRequest request){

        System.err.println("SubmissionTwo.........controller............."+jobhunter);

        HashMap<Object, String> map = userService.registerStaffTwo(jobhunter,request);

        System.err.println("SubmissionTwo.........controller............."+map);

        return map;
    }


    //求职者User表基本信息添加
    @PostMapping(value = "/SubmissionOne",consumes = "application/json")
    @ResponseBody
    public HashMap<Object,String> SubmissionOne(@RequestBody User user,@RequestParam String flag,HttpServletRequest request){

        System.err.println("SubmissionOne......................"+user);

        System.err.println("SubmissionOne...........flag..........."+flag);


        HashMap<Object, String> map = userService.registerStaffOne(user,flag,request);

        System.err.println("SubmissionOne......................"+map);

        return map;
    }




    //发送短信
    @RequestMapping("/sendSms")
    @ResponseBody
    public HashMap<String,Object> sendSmsOne(String phone, HttpServletRequest request){


        System.err.println("进来了sendSms》》》》》》》》"+phone);

        return userService.sendSms(phone,request);

    }



    //手机短信进入
    @PostMapping(value = "/smsEnter",consumes = "application/json")
    @ResponseBody
    public HashMap<String,Object> smsEnter(@RequestBody UserVo userVo, HttpServletRequest request){

        HashMap<String, Object> map = userService.smsEnter(userVo,request);

        System.err.println("smsEnter......................"+map);

        return map;

    }



}
