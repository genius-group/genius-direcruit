package com.sfac.geniusdirecruit.hsyetem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class JobCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobCategoryId;
    @Column
    private String jobCategoryName;
}
