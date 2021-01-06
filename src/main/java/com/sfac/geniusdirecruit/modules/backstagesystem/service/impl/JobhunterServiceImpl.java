package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobhunterDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Jobhunter;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.JobhunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:26
 * 概要：
 * XXXXX
 */
@Service
public class JobhunterServiceImpl implements JobhunterService {
    @Autowired
    private JobhunterDao jobhunterDao;
    @Override
    public List<Jobhunter> getJobhunter() {

        return jobhunterDao.getJobhunter();
    }

    @Override
    public PageInfo<Jobhunter> getJobhunterBySearchBean(SearchBean searchBean) {
        return jobhunterDao.getJobhunterBySearchBean();
    }
}
