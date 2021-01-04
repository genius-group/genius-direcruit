package com.sfac.geniusdirecruit.hsyetem.entity;

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Resume extends ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resumeId;
    @Column
    private String resumeName;
    @Column
    private String photoUrl;
    @Column
    private String schoolName;
    @Column
    private String state;//（离职-随时到岗、在职-暂不考虑、在职-考虑机会、在职-月内到岗）
    @Column
    private String sex;
    @Column
    private Date birth;
    @Column(length = 11)
    private String tel;
    @Column
    private String email;
    @Column
    private String personalAdv;
    @Column
    private String type;//(全职，兼职)
    @Column
    private String workCity;
    @Column
    private String wantedJob;
    @Column
    private String wantedTrade;
    @Column
    private String salary;
    @Column
    private String internship;//(实习经历)
    @Column
    private String education;//(初中及以下、中专/中技、高中、大专、本科、研究生、博士),
    @Column
    private Date timeQuantum;//(时间段)

}
