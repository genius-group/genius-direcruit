package com.sfac.geniusdirecruit.modules.backstagesystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/30 23:01
 * 概要：
 * 求职者
 */
@Entity
@Table(name = "job_hunter")
@Data
public class JobHunter {
    //创建主键
    @Id//主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobHunterId;
    private Integer userId;

    //求职者姓名
    @Column
    private String jobHunterName;

    //性别
    @Column
    private String sex;

    //生日
    @Column
    private String birth;

    //头像
    @Column
    private String photo;

    //学历
    @Column
    private String educate;

    //邮箱
    @Column
    private String email;

    //地址
    @Column
    private String address;
}
