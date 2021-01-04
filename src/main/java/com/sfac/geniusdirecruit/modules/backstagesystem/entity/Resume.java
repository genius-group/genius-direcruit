package com.sfac.geniusdirecruit.modules.backstagesystem.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: yzs
 * @Date: 2020/12/31 0:09
 * 概要：
 * 简历
 */
@Entity
@Table(name = "resume")
@Data
public class Resume {
    //创建主键
    @Id//主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resumeId;
    private Integer jobHunterId;

    //简历名称
    @Column
    private String resumeName;

    //姓名
    @Column
    private String name;

    //寸照
    @Column
    private String url;

    //学校名称
    @Column
    private String schoolName;

    //当前求职状态（离职-随时到岗、在职-暂不考虑、在职-考虑机会、在职-月内到岗）
    @Column
    private String state;

    //性别
    @Column
    private String sex;

    //出生年月
    @Column
    private String birthDate;

    //邮箱
    @Column
    private String email;

    //电话
    @Column
    private String tel;

    //个人优点
    @Column
    private String personalAdv;

    //求职类型（全职、兼职）
    @Column
    private String type;

    //工作城市
    @Column
    private String workCity;

    //期望工作
    @Column
    private String wantedJob;

    //期望行业
    @Column
    private String wantedTrade;

    //薪资要求
    @Column
    private String salary;

    //实习经历
    @Column
    private String internship;

    //学历(初中及以下、中专/中技、高中、大专、本科、研究生、博士)
    @Column
    private String education;

    //专业
    @Column
    private String major;

    //时间段
    @Column
    private String timeQuantum;
}
