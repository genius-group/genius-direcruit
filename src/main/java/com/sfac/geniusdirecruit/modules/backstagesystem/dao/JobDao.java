package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Job;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.vo.CompanyVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @Author: yzs
 * @Date: 2020/12/31 16:17
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface JobDao {
    //添加job
    @Insert("Insert into job (job_category_id,job_name,description,pay,numbers,degree,expiry_date,area,address,view_count,release_time) " +
            "values (#{jobCategoryId},#{jobName},#{description},#{pay},#{numbers},#{degree},#{expiryDate},#{area},#{address},#{viewCount},#{releaseTime})")
    void insertJob(Job job);

    //根据jobName查询job
    @Select("select* from job where job_name=#{jobName}")
    Job selectJobByJobName(String jobName);

    //根据jobId查询job
    @Select("select* from job where job_id=#{jobId}")
    Job getJobIdById(Integer jobId);

    //查询job
    @Select("select * from job")
    List<Job> getJob();

    //分页排序查询job
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
    //修改job
    @Update("update job set job_category_id=#{jobCategoryId},job_name=#{jobName},description=#{description},pay=#{pay},numbers=#{numbers},degree=#{degree},expiry_date=#{expiryDate},area=#{area},address=#{address},view_count=#{viewCount},release_time=#{releaseTime} where job_id = #{jobId}")
    void updateJob(Job job);

    //删除job
    @Delete("delete from job where job_id = #{jobId}")
    void deleteJobById(int jobId);

    //将url增加到简历表中
    @Insert("insert into resume (url) values (#{url})")
    int insertByUrl(String url);

    //通过jobId批量查询jobName
    @Select({"<script>" +
            "select job_name from job where job_id in " +
            "<foreach item = 'item' index = 'index' collection = 'jobIdList' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>"+
            "</script>"})
    List<String> selectJobNameByIds(@Param("jobIdList") List<Integer> jobIdList);

    @Select("<script>" +
            "SELECT\n" +
            "\tcompany.user_id user_id, company.company_name company_name,job_category.job_category_name job_category_name,job.pay pay, job.numbers numbers,job.job_name job_name,company_job.`status` `status`,company_job.company_job_id company_job_id\n" +
            "FROM\n" +
            "\tcompany\n" +
            "\tINNER JOIN\n" +
            "\tcompany_job\n" +
            "\tON \n" +
            "\t\tcompany.company_id = company_job.company_id\n" +
            "\tINNER JOIN\n" +
            "\tjob\n" +
            "\tON \n" +
            "\t\tcompany_job.job_id = job.job_id\n" +
            "\tINNER JOIN\n" +
            "\tjob_category\n" +
            "\tON \n" +
            "\t\tcompany_job.company_job_id = job_category.job_category_id\n"
            + "<where> "
            + "<if test='userId!= null'>"
            + " and user_id = #{userId} "
            + "</if>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (status like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='order != \"\" and order != null'>"
            + " order by ${order} ${direction}"
            + "</when>"
            + "<otherwise>"
            + " order by job_name desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<CompanyVo> getCompanyBySearchBean(CompanyVo companyVo);

    @Delete("delete from company_job where company_job_id = #{companyJobId}")
    void deleteCompanyJobById(int companyJobId);
}
