package com.sfac.geniusdirecruit.hsyetem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Jobhunter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobhunterID;
    @Column
    private int userId;
    @Column
    private String jobhunterName;
    @Column
    private char sex;
    @Column
    private Date birth;
    @Column
    private String photo;
    @Column
    private String educat;
    @Column
    private String email;
    @Column
    private String address;
}
