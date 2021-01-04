package com.sfac.geniusdirecruit.modules.backstagesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/30 23:40
 * 概要：
 * 职位
 */
@Entity
@Table(name = "job")
@Data
public class Job {
    //创建主键
    @Id//主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;
    private Integer jobCategoryId;

    //职位名称
    @Column
    private String jobName;

    //职位描述
    @Column
    private String description;

    //工作薪酬
    @Column
    private String pay;

    //招聘人数
    @Column
    private String numbers;

    //学历要求
    @Column
    private String degree;

    //有效期限
    @Column
    private String expiryDate;

    //工作区域
    @Column
    private String area;

    //工作地址
    @Column
    private String address;

    //游览次数
    @Column
    private String viewCount;

    //发布时间
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime releaseTime;

    @Transient
    private List<Company> companies;

}
