package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Blogroll;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:14
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface BlogrollDao {
    //添加blogroll
    @Insert("insert into blogroll (title,content,create_time) values (#{title},#{content},#{createTime})")
    void insertBlogroll(Blogroll blogroll);

    //根据blogrollName查询
    @Select("select * from blogroll where blogroll_name = #{blogrollName}")
    Blogroll selectblogrollNameByBlogrollName(String blogrollName);
    //查询news数据
    @Select("select * from blogroll")
    List<Blogroll> getBlogroll();

    //分页排序
    @Select("<script>" +
            "select * from blogroll "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (title like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='order != \"\" and order != null'>"
            + " order by ${order} ${direction}"
            + "</when>"
            + "<otherwise>"
            + " order by create_time desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Blogroll> getBlogrollBySearchBean(SearchBean searchBean);

    //根据newId查询
    @Select("select * from blogroll where blogroll_id = #{blogrollId}")
    Blogroll getBlogrollById(Integer newId);
    @Update("update news set title=#{title},content=#{content},create_time=#{createTime} where new_id = #{newId}")
    void updateBlogroll(Blogroll blogroll);
    //删除news
    @Delete("delete from blogroll where blogroll_id = #{blogrollId}")
    void deleteBlogrollById(Integer blogrollId);

/*


    */
}
