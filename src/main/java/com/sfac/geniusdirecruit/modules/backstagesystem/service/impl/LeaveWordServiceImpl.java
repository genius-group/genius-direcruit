package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.LeaveWordDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.LeaveWord;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.News;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.LeaveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:27
 * 概要：
 * XXXXX
 */
@Service
public class LeaveWordServiceImpl implements LeaveWordService {
    @Autowired
    private LeaveWordDao leaveWordDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JobDao jobDao;

    @Override
    public ResultEntity<LeaveWord> addLeaveWord(LeaveWord leaveWord) {
        News temp = leaveWordDao.selectLeaveWordByTitle(leaveWord.getTitle());

        if (temp == null) {
            leaveWordDao.insertLeaveWord(leaveWord);
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "Insert success", leaveWord);
        }

        return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status,
                "Category name is repeat.");
    }

    @Override
    public ResultEntity<Object> deleteLeaveWordById(Integer leaveWordId) {
        leaveWordDao.deleteLeaveWordById(leaveWordId);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Delete success");
    }

    @Override
    public List<LeaveWord> getLeaveWord() {
        return null;
    }

    @Override
    public PageInfo<LeaveWord> getLeaveWordBySearchBean(SearchBean searchBean) {
        return null;
    }
}
