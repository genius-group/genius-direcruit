package com.sfac.geniusdirecruit.hsyetem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    @Column
    private int jobCategoryId;
    @Column
    private String jobName;
    @Column
    private String describe;
    @Column
    private double pay;
    @Column
    private int numbers;//(招聘人数)
    @Column
    private String degree;
    @Column
    private String expiryDate;
    @Column
    private  String area;
    @Column
    private  String address;
    @Column
    private  String viewCount;
    @Column
    private  int companyId;
    @Column
    private  String releaseTime;
}
