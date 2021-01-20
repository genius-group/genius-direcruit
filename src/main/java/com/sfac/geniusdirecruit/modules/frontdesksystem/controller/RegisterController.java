package com.sfac.geniusdirecruit.modules.frontdesksystem.controller;
/*
 * @projectName: genius-direcruit
 * @documentName: RegisterController
 * @author: WJM
 * @date:2021/1/6 14:05
 */

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.utile.SmsSend;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Company;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Jobhunter;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.vo.UserVo;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.JobhunterService;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    JobhunterService jobhunterService;

    /*
     *  127.0.0.1:8080/frontdesk/GoJobHunterUpdate
     * */

    //访问修改页面
    @RequestMapping("GoJobHunterUpdate")
    public String goJobhunterUpdate(Model model, HttpServletRequest request) {

        model.addAttribute("jobhunterList", jobhunterService.findAllJobhunter(request));

        Subject subject = SecurityUtils.getSubject();
        request.getSession().setAttribute("user",subject.getPrincipal());

        System.err.println("=======GoJobHunterUpdate==========>"+jobhunterService.findAllJobhunter(request));

        return "frontdesk/jobhunterUpdate";

    }




    //修改(提交)
    @PostMapping(value = "/submitUpdate",consumes = "application/json")
    @ResponseBody
    public HashMap<Object,String> submitUpdate(@RequestBody Jobhunter jobhunter,HttpServletRequest request,Model model){

        System.err.println("submitUpdate.........controller............."+jobhunter);

        HashMap<Object, String> map = jobhunterService.updateJobhunter(jobhunter);

        User user1 = (User)request.getSession().getAttribute("user");
        System.err.println("submitUpdate.........user1............."+user1);
        model.addAttribute("user",user1);

        System.err.println("submitUpdate.........controller............."+map);

        return map;
    }


    /*
     *  127.0.0.1:8080/frontdesk/ChangePassword
     * */
    @RequestMapping("/ChangePassword")
    public String changePassword() {

        return "frontdesk/changePassword";

    }

    //修改密码
    @PostMapping(value = "/ChangePasswordDo",consumes = "application/json")
    @ResponseBody
    public HashMap<Object,String> changePassword(@RequestParam String newPassword,HttpServletRequest request){

        System.err.println("changePassword...........newPassword..........."+newPassword);


        HashMap<Object, String> map = userService.ChangePassword(newPassword,request);

        System.err.println("changePassword............map.........."+map);

        return map;
    }




    /*
     *  127.0.0.1:8080/frontdesk/GoIndex
     * */

    @RequestMapping("/GoIndex")
    public String goIndex(HttpServletRequest request,Model model) {

        User user1 = (User)request.getSession().getAttribute("user");
        System.err.println("goIndex.........user1............."+user1);
        model.addAttribute("user",user1);

        return "frontdesk/index";

    }




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




    //头像上传
    @RequestMapping(value="/uploadPicture",method = RequestMethod.POST)
    @ResponseBody         //@RequestParam("file") 数据库中该属性的名称
    public ResultEntity<String> execute(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request) {
        return jobhunterService.uploadUserFile(file);
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
