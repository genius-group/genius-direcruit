package com.sfac.geniusdirecruit.modules.backstagesystem.entity.vo;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import lombok.Data;
import javax.persistence.Column;

@Data
public class CompanyVo extends SearchBean {
    private Integer userId;

    private Integer companyId;

    private Integer jobId;

    private Integer jobCategoryId;

    //职位名称
    private String jobName;
    //企业名称
    private String companyName;

    //职位类别名称
    private String jobCategoryName;

    //公司地址
    private String address;

    //工作薪酬
    private String pay;

    private String status;

    //公司描述
    private String description;

    private String degree;

    private String numbers;

    //信用代码
    private String creditCode;

    //公司性质
    private String nature;

}
