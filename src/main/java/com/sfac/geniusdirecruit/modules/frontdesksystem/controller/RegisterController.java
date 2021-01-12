package com.sfac.geniusdirecruit.modules.frontdesksystem.controller;/*
 * @projectName: genius-direcruit
 * @documentName: RegisterController
 * @author: WJM
 * @date:2021/1/6 14:05
 */

import com.sfac.geniusdirecruit.common.utile.SmsSend;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

/*
*  127.0.0.1:8080/frontdesk/SubmissionPage
* */

    @RequestMapping("/SubmissionPage")
    public String submissionPage() {

        return "frontdesk/registerSubmission";

    }



    //求职者注册
    @PostMapping(value = "/SubmissionOne",consumes = "application/json")
    @ResponseBody
    public HashMap<Object,String> SubmissionTwo(@RequestBody User user){

        System.err.println("SubmissionOne......................"+user);

        HashMap<Object, String> map = userService.registerStaff(user);

        System.err.println("SubmissionOne......................"+map);

        return map;
    }




    //已弃用
    @PostMapping(value = "/SubmissionTwo",consumes = "application/json")
    @ResponseBody
    public HashMap<Object,String> SubmissionOne(@RequestBody User user){

        HashMap<Object, String> map = new HashMap<Object, String>();

        System.err.println("进来了SubmissionOne》》》》》》》》"+user);

        //判断注册用户名是否唯一
        if (userService.isUserExist(user.getUserName())){
            map.put("info","该用户名已被注册，请重新输入");

        }else{

            //新增注册后的用户
            userService.insertRegisterUser(user);
            map.put("info","注册成功");
        }

      return map;
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
    * 127.0.0.1:8080/frontdesk/sendSms ---- get
    *"phone":tel
    *
     * */


    //发送短信,待改写
//    @GetMapping("/sendSms/{phone}")
    @RequestMapping("/sendSms")
    @ResponseBody
    public HashMap<String,Object> sendSms(String phone, HttpServletRequest request){

        System.err.println("进来了sendSms》》》》》》》》"+phone);


        HashMap<String,Object> map = new HashMap<String,Object>();
        Random rd = new Random();
        int code = rd.nextInt(1000);
        //发送短信
        String info = smsSend.send(phone,code+"");
        //判断是否成功
        if(info.equals("OK")){
            //存入session中
            request.getSession().setAttribute("smsCode",code);
            map.put("info","短信发送成功");
        }else{
            map.put("info","短信发送失败");
        }

//        request.getSession().setAttribute("smsCode",1111);



        return map;
    }



    //手机短信进入，待改写
    @RequestMapping("/smsLogin/{code}/{tel}")
    @ResponseBody
    public HashMap<String,Object> smsLogin(@PathVariable String code, @PathVariable String tel, HttpServletRequest request, User user){
        HashMap<String,Object> map = new HashMap<String,Object>();
        String sessionCode = request.getSession().getAttribute("smsCode")+"";

        if(code.equals(sessionCode)){

            System.err.println("smsLogin>>>>>>>>>>>>>>>"+tel);

            System.err.println("smsLogin>>>>>>>>>>>>>>>"+code);

            //根据电话去查询用户，如果已注册，则去登录页面
            //判断注册用户是否存在，根据输入电话

            if (userService.selectUserByTel(tel)){
                System.err.println("smsLogin>>>>>>>>>>>>>>>进来找打啊！！");
                map.put("info","用户已注册，请登录");
            }else {

                //新增注册后的用户，那么就把注册的电话号码送入数据库
//                userService.insertRegisterUserTel(tel);
                map.put("info","登入成功");
            }


        }else{
            map.put("info","登入失败");
        }

        return map;
    }




}
