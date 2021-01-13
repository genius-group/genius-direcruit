package com.sfac.geniusdirecruit.modules.backstagesystem.pagecontroller;

import com.sfac.geniusdirecruit.modules.backstagesystem.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    private JobService jobService;

    //定义文件上传保存的路径
    @Value("${file.address}")
    private String fileAddres;

    @RequestMapping("/jobsPage")
    public String jobsPage(ModelMap modelMap) {
        modelMap.put("template", "backstagesystem/jobs");
        return "common/managerIndex";
    }

    @GetMapping("/index")
    public String index1(Model map, @RequestParam(required = false,defaultValue = "1") int page) {
        map.addAttribute("listUser", jobService.findAll(page));
        return "frontdesk/index";
    }
    @ResponseBody
    @RequestMapping("/upload")
    public String upload(MultipartFile file) {
        //文件上传的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取文件的名称
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        String date = sdf.format(new Date());
        //文件存储的新路径
        String url = fileAddres + "/" + date + "/" + newName;
        //上传简历的路径保存到简历表中去
        //System.out.println("我的上传路径:" + url);
        int n =  jobService.insertByUrl(url);
        System.out.println(n);
        File folder = new File(url);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            file.transferTo(folder);
            return url;
        } catch (IOException var10) {
            var10.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/download")
    @ResponseBody
    public String fileDownLoad(HttpServletResponse response, @RequestParam(value = "fileName",required = false) String fileName){
        File file = new File("D://springbootimage/2021-01-12/69ca6708-39e5-40b8-b40f-e047182ddc6c.jpg");
        fileName = "69ca6708-39e5-40b8-b40f-e047182ddc6c.jpg";
        if(!file.exists()){
            return "下载文件不存在";
        }
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        OutputStream os  = null;
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
//            log.error("{}",e);
            return "下载失败";
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "下载成功";
    }

    @GetMapping("/resume")
    public String jianli() {
        return "frontdesk/resume";
    }

    @RequestMapping("/test")
    public String test() {
        return "frontdesk/test";
    }
}
