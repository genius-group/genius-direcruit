package com.sfac.geniusdirecruit.hsyetem.entity;
/**
 * 留言信息
 */

import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class LeaveWord extends ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveWordId;
    @Column
    private int userId;
    @Column
    private int jobId;
    @Column
    private String content;
    @Column
    private String title;
    @Column
    private Date leaveTime;

}
