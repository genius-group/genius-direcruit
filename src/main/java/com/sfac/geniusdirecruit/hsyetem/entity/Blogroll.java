package com.sfac.geniusdirecruit.hsyetem.entity;
/**
 * 友情链接
 */

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Blogroll extends ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogrollId;
    @Column
    private String blogrollName;
    @Column
    private String blogrollLogo;
    @Column
    private String blogrollUrl;
}
