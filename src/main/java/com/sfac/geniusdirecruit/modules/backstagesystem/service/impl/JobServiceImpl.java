package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobsDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Job;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashMap;
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

    @Autowired
    private JobsDao jobsDao;

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
            jobDao.updateJob(job);
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "update success", job);
        }
            return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status,
                "job name is repeat.");
    }

    @Override
    public ResultEntity<Object> deleteJobById(Integer jobId) {
       jobDao.deleteJobById(jobId);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Delete success");
    }

    @Override
    public HashMap<String, Object> findAll(int currentPage) {
        HashMap<String, Object> map = new HashMap();
        Job job = new Job();
        job.setPage(currentPage);
        Pageable pageable = PageRequest.of(job.getPage() - 1, job.getRow(), Sort.by(new String[]{"numbers"}).descending());
        Page<Job> userInfoPage = jobsDao.findAll(pageable);
        map.put("curPage", userInfoPage.getNumber() + 1);
        if (userInfoPage.getNumber() < 1) {
            map.put("prePage", userInfoPage.getNumber() + 1);
        } else {
            map.put("prePage", userInfoPage.getNumber());
        }

        if (userInfoPage.getNumber() + 2 >= userInfoPage.getTotalPages()) {
            map.put("nextPage", userInfoPage.getTotalPages());
        } else {
            map.put("nextPage", userInfoPage.getNumber() + 2);
        }

        map.put("totalPages", userInfoPage.getTotalPages());
        map.put("totalElements", userInfoPage.getTotalElements());
        map.put("list", userInfoPage.getContent());
        return map;
    }

    @Override
    public int insertByUrl(String url) {
        return jobDao.insertByUrl(url);
    }
}


