package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Job;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.JobService;
import com.sfac.geniusdirecruit.modules.common.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:25
 * 概要：
 * XXXXX
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;
    @Override
    public ResultEntity<Job> insertJob(Job job) {
        Job temp = jobDao.selectJobByJobName(job.getJobName());
        if (temp == null) {
            jobDao.insertJob(job);
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "Insert success", job);
        }
            return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status,
                "job name is repeat.");
    }

    @Override
    public Job getJobIdById(Integer jobId) {
        return jobDao.getJobIdById(jobId);
    }

    @Override
    public List<Job> getJob() {
        return jobDao.getJob();
    }

    @Override
    public PageInfo<Job> getJobBySearchBean(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(jobDao.getJobBySearchBean(searchBean))
                .orElse(Collections.emptyList()));

    }

    @Override
    public ResultEntity<Job> updateJob(Job job) {

        Job temp = jobDao.selectJobByJobName(job.getJobName());
        if (temp == null) {
            jobDao.updateJob();
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "update success", job);
        }
            return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status,
                "job name is repeat.");
    }

    @Override
    public ResultEntity<Object> deleteJobById(Integer jobId) {
        return jobDao.deleteJobById();
    }

}
