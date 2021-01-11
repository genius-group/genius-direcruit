package com.sfac.geniusdirecruit.modules.backstagesystem.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Company;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:10
 * 概要：
 * XXXXX
 */
@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 查看企业信息
     * http://127.0.0.1:8080/api/companies------------get
     */
    @GetMapping("/companies")
    public List<Company> selectCompanies(){
        return companyService.selectCompanies();
    }

    /**
     * 查看企业信息并分类
     * http://127.0.0.1:8080/api/companies------------post
     * {"currentPage":1, "pageSize":2, "order":"credit_code", "direction":"desc", "keyWord":""}
     */
    @PostMapping(value = "/companies",consumes = "application/json")
    public PageInfo<Company> getCompaniesBySearchBean(@RequestBody SearchBean searchBean){
        return companyService.getCompaniesBySearchBean(searchBean);
    }

    /**
     * 编辑企业信息(预修改信息的显示)
     * http://127.0.0.1:8080/api/company/1-----------get
     */
    @GetMapping("/company/{companyId}")
    public Company getCompanyByCompanyId(@PathVariable int companyId){
        return companyService.getCompanyByCompanyId(companyId);
    }

    /**
     * 编辑企业信息
     * http://127.0.0.1:8080/api/company-----------put
     * {"companyId":1,"userId":3,"companyName":"海尔","address":"上海","description":"家用电器","creditCode":"222333","nature":"上市"}
     */
    @PutMapping(value = "/company",consumes = "application/json")
    public ResultEntity<Company> editCompany(@RequestBody Company company){
        return companyService.editCompany(company);
    }

}
