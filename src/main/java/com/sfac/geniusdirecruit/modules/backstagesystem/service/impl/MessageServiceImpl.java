package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.MessageDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Message;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.News;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.vo.MessageVo;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:27
 * 概要：
 * XXXXX
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JobDao jobDao;

    @Override
    public ResultEntity<MessageVo> addMessage(MessageVo messageVo) {

        News temp = messageDao.selectLeaveWordByTitle(messageVo.getTitle());

        if (temp == null) {
            Message message = new Message();
            message.setContent(messageVo.getContent());
            message.setJobId(messageVo.getJobId());
            message.setMessageTime(messageVo.getMessageTime());
            message.setTitle(messageVo.getTitle());
            message.setUserId(messageVo.getUserId());
            messageDao.insertMessage(message);
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "Insert success", messageVo);
        }
        return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status,
                "Title name is repeat.");
    }

    @Override
    public ResultEntity<Object> deleteMessageById(Integer leaveWordId) {
        messageDao.deleteMessageById(leaveWordId);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Delete success");
    }

    @Override
    public List<Message> getMessage() {
        return messageDao.getMessage();
    }

    @Override
    public PageInfo<Message> getMessageBySearchBean(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(messageDao.getMessageBySearchBean(searchBean))
                .orElse(Collections.emptyList()));
    }
}
