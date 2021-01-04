package com.sfac.geniusdirecruit.hsyetem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    @Column
    private int userId;
    @Column
    private String companyName;
    @Column
    private String address;
    @Column
    private String describe;
    @Column
    private String creditRating;
    @Column
    private String nature;
}
