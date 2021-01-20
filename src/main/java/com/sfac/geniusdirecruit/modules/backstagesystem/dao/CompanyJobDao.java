package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.sfac.geniusdirecruit.modules.backstagesystem.entity.CompanyJob;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: yzs
 * @Date: 2021/1/2 20:15
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface CompanyJobDao {
    @Delete("delete from company_job where company_id = #{companyId}")
    void deleteCompanyJobsByCompanyId(Integer companyId);
    @Insert("insert into company_job (company_id,job_id,status) values (#{companyId},#{jobId},#{status})")
    void insertCompanyJob(CompanyJob companyJob);
}
