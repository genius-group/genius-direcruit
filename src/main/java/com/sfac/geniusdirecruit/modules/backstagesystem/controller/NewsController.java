package com.sfac.geniusdirecruit.modules.backstagesystem.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.News;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:12
 * 概要：
 * XXXXX
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    //添加news http://127.0.0.1:8080/news/addNews
    //{"title":"习大大","content":"ygfeueuigvdu","createTime":"2020-01-05 14:33:34"}
    @PostMapping(value = "/addNews", consumes = "application/json")
    public ResultEntity<News> insertNews(@RequestBody News news) {
        return newsService.insertNews(news);
    }

    //http://127.0.0.1:8080/news/readNewses  get请求
    @GetMapping("/readNewses")
    public List<News> getNews() {
        return newsService.getNews();
    }

    //http://127.0.0.1:8080/news/readNewses   post请求
    // {"currentPage":3, "pageSize":2, "order":"create_time", "direction":"desc", "keyWord":""}
    @PostMapping(value = "/readNewses", consumes = "application/json")
    public PageInfo<News> getNewsBySearchBean(
            @RequestBody SearchBean searchBean) {
        return newsService.getNewsBySearchBean(searchBean);
    }

    //预编辑  http://127.0.0.1:8080/news/news/1
    @GetMapping("/news/{newId}")
    public News getnewsById(@PathVariable("newId") Integer newId) {
        return newsService.getNewsById(newId);
    }

    //编辑  http://127.0.0.1:8080/news/editNews
    @PutMapping(value = "/editNews", consumes = "application/json")
    public ResultEntity<News> updateNews(@RequestBody News news) {
        return newsService.updateNews(news);
    }

    //删除news http://127.0.0.1:8080/news/deleteNews/1
    @DeleteMapping("/deleteNews/{newId}")
    public ResultEntity<Object> deleteNewsById(@PathVariable("newId") Integer newId) {
        return newsService.deleteNewsById(newId);
    }
}

