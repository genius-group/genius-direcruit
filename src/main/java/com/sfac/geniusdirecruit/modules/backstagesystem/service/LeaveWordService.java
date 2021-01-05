package com.sfac.geniusdirecruit.modules.backstagesystem.service;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.LeaveWord;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:27
 * 概要：
 * XXXXX
 */
public interface LeaveWordService {
    ResultEntity<LeaveWord> addLeaveWord(LeaveWord leaveWord);

    ResultEntity<Object> deleteLeaveWordById(Integer leaveWordId);

    List<LeaveWord> getLeaveWord();

    PageInfo<LeaveWord> getLeaveWordBySearchBean(SearchBean searchBean);
}
