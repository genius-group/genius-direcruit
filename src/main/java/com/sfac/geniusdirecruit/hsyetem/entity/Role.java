package com.sfac.geniusdirecruit.hsyetem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    @Column
    private String roleName;
    @Column
    private String roleDescribe;
}
