package com.sfac.geniusdirecruit.modules.backstagesystem.service;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Jobhunter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:26
 * 概要：
 * XXXXX
 */
public interface JobhunterService {

    List<Jobhunter> getJobhunter();

    PageInfo<Jobhunter> getJobhunterBySearchBean(SearchBean searchBean);
    //头像上传
    ResultEntity uploadUserFile(MultipartFile file);

    Jobhunter findAllJobhunter(HttpServletRequest request);

    HashMap<Object, String> updateJobhunter(Jobhunter jobhunter);

}
