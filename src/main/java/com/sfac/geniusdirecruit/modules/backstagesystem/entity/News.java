package com.sfac.geniusdirecruit.modules.backstagesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: yzs
 * @Date: 2020/12/30 23:56
 * 概要：
 * 新闻
 */
@Entity
@Table(name = "news")
@Data
public class News {
    //创建主键
    @Id//主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newId;

    //新闻标题
    @Column
    private String title;

    //新闻内容
    @Column
    private String content;

    //创建时间
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime createTime;
}
