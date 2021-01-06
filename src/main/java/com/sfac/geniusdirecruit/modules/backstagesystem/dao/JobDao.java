package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Job;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:17
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface JobDao {
    @Insert("Insert into job (job_category_id,job_name,description,pay,numbers,degree,expiry_date,area,address,view_count,release_time) " +
            "values (#{jobCategoryId},#{jobName},#{description},#{pay},#{numbers},#{degree},#{expiryDate},#{area},#{address},#{viewCount},#{releaseTime})")
    ResultEntity<Job> insertJob(Job job);
    @Select("select* from job where job_name=#{jobName}")
    Job selectJobByJobName(String jobName);
    @Select("select* from job where job_id=#{jobId}")
    Job getJobIdById(Integer jobId);
    @Select("select * from job")
    List<Job> getJob();
    //分页排序
    @Select("<script>" +
            "select * from job "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (job_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='order != \"\" and order != null'>"
            + " order by ${order} ${direction}"
            + "</when>"
            + "<otherwise>"
            + " order by job_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Job> getJobBySearchBean(SearchBean searchBean);
    @Update("update job set #{jobCategoryId},jobName=#{jobName},description=#{description},pay=#{pay},numbers=#{numbers},degree=#{degree},expiryDate=#{expiryDate},area=#{area},address=#{address},viewCount=#{viewCount},releaseTime=#{releaseTime} where job_id = #{jobId}")
    ResultEntity<Job> updateJob();
    @Delete("delete from job where job_id = #{jobId}")
    ResultEntity<Object> deleteJobById();
}
