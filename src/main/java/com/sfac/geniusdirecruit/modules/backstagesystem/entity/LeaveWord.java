package com.sfac.geniusdirecruit.modules.backstagesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: yzs
 * @Date: 2020/12/31 0:02
 * 概要：
 * 留言
 */
@Entity
@Table(name = "leave_word")
@Data
public class LeaveWord {
    //创建主键
    @Id//主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveWordId;
    private Integer userId;
    private Integer jobId;

    //留言内容
    @Column
    private String content;

    //留言标题
    @Column
    private String title;

    //留言时间
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime leaveTime;
}
