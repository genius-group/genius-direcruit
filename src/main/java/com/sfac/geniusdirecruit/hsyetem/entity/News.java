package com.sfac.geniusdirecruit.hsyetem.entity;

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class News extends ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newId;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Date createDate;
}
