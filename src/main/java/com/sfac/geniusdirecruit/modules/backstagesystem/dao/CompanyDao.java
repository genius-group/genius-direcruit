package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:16
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface CompanyDao {
    @Select("select * from company")
    List<Company> selectCompanies();

    @Select("<script>" +
            "select * from company "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (user_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='order != \"\" and order != null'>"
            + " order by ${order} ${direction}"
            + "</when>"
            + "<otherwise>"
            + " order by credit_code desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Company> getCompaniesBySearchBean(SearchBean searchBean);

    @Select("select * from company where company_id = #{companyId}")
    Company getCompanyByCompanyId(int companyId);

    @Update("update company set user_id=#{userId},company_name = #{companyName},address =#{address}, description=#{description},credit_code =#{creditCode}, nature =#{nature} where company_id = #{companyId}")
    void editCompany(Company company);

    @Select("select * from company where user_id = #{userId}")
    Company selectCompanyByUserId(Integer userId);

    @Delete("delete from company where user_id = #{userId}")
    void deleteCompanyByUserId(Integer userId);
}
