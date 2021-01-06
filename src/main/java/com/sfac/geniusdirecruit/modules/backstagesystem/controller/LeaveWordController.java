package com.sfac.geniusdirecruit.modules.backstagesystem.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.LeaveWord;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.News;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.LeaveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:11
 * 概要：
 * XXXXX
 */

@RestController
@RequestMapping("/leaveWord")
public class LeaveWordController {
    @Autowired
    LeaveWordService leaveWordService;

    //添加LeaveWord http://127.0.0.1:8080/leaveWord/addLeaveWord
    @PostMapping(value = "/addLeaveWord", consumes = "application/json")
    public ResultEntity<LeaveWord> insertLeaveWord(@RequestBody LeaveWord leaveWord) {
        return leaveWordService.addLeaveWord(leaveWord);
    }

    //删除LeaveWord http://127.0.0.1:8080/leaveWord/deleteLeaveWord/1
    @DeleteMapping("/deleteLeaveWord/{newId}")
    public ResultEntity<Object> deleteLeaveWordById(@PathVariable("newId") Integer leaveWordId) {
        return leaveWordService.deleteLeaveWordById(leaveWordId);
    }

    //查询LeaveWord
    //http://127.0.0.1:8080/leaveWord/readLeaveWords  get请求
    @GetMapping("/readLeaveWords")
    public List<LeaveWord> getLeaveWord() {
        return leaveWordService.getLeaveWord();
    }
    //分页查询LeaveWord
    //http://127.0.0.1:8080/leaveWord/readLeaveWords   post请求
    // {"currentPage":3, "pageSize":2, "order":"create_time", "direction":"desc", "keyWord":""}
    @PostMapping(value = "/readLeaveWords", consumes = "application/json")
    public PageInfo<LeaveWord> getLeaveWordBySearchBean(
            @RequestBody SearchBean searchBean) {
        return leaveWordService.getLeaveWordBySearchBean(searchBean);
    }
}
