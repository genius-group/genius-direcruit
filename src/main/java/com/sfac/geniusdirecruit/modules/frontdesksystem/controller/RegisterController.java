package com.sfac.geniusdirecruit.modules.frontdesksystem.controller;/*
 * @projectName: genius-direcruit
 * @documentName: RegisterController
 * @author: WJM
 * @date:2021/1/6 14:05
 */

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.utile.SmsSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Random;


@Controller
@RequestMapping("/frontdesk")
public class RegisterController {

    @Autowired
    SmsSend smsSend;

    /**
     * 127.0.0.1:8080/frontdesk/register ---- get
     *
     * http://localhost:8080/frontdesk/register----get
     */
//    @GetMapping("/register")
    @RequestMapping("/register")
    public String registerPage() {

//        modelMap.put("template", "account/register");
        return "frontdesk/register";

    }

    /*
    * 127.0.0.1:8080/frontdesk/sendSms ---- get
    *"phone":tel
    * */

    //发送短信
    @GetMapping("/sendSms/{phone}")
    @ResponseBody
    public HashMap<String,Object> sendSms(@PathVariable String phone, HttpServletRequest request){
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

        return map;
    }



}
