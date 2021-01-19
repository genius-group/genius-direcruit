package com.sfac.geniusdirecruit.modules.frontdesksystem.controller;

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.frontdesksystem.service.JobFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;



@Controller
@RequestMapping("/frontjobs")
public class JobFrontController {

    @Autowired(required = false)
    private JobFrontService jobfrontService;

    //跳转到主站
    @GetMapping("/index")
    public String index1(Model map,HttpServletRequest request, @RequestParam(required = false,defaultValue = "1") int page) {
        map.addAttribute("listUser", jobfrontService.findAll(page));
        User user1 = (User)request.getSession().getAttribute("user");
        map.addAttribute("user",user1);
        return "/frontdesk/index";
    }

    @RequestMapping("/test")
    public String test(Model map) {
        map.addAttribute("job", jobfrontService.findByResumeName());
        return "/frontdesk/resumeDownloadTest";
    }

    //文件上传
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    @ResponseBody         //@RequestParam("file") 数据库中该属性的名称
    public ResultEntity<String> execute(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request) {
        return jobfrontService.uploadUserFile(file);
    }

    //文件下载
    @RequestMapping("/download")
    @ResponseBody
    public ResultEntity<String> download(@RequestParam(required = false) String fileName, HttpServletRequest request,
                                         HttpServletResponse response) throws IOException {
        return jobfrontService.downloadUserFile(fileName,response);
    }

    //简历页面
    @GetMapping("/resume")
    public String resume() {
        return "/frontdesk/resume";
    }

    //岗位详情页面
    @RequestMapping("/job")
    public String job(Integer jobId){

        return null;
    }

    //条件搜索
    @GetMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1") int page, String search,Model map,HttpServletRequest request){
        if (search!=null){map.addAttribute("listUser", jobfrontService.findBySearch(page,search,request));
            User user1 = (User)request.getSession().getAttribute("user");
            map.addAttribute("user",user1);
            map.addAttribute("条件",request.getSession().getAttribute("条件"));
        }else {
            String search1 = (String) request.getSession().getAttribute("条件");
            map.addAttribute("listUser", jobfrontService.findBySearch(page,search1,request));
            User user1 = (User)request.getSession().getAttribute("user");
            map.addAttribute("user",user1);
            map.addAttribute("条件",request.getSession().getAttribute("条件"));

        }

        return "/frontdesk/index";
    }
}
