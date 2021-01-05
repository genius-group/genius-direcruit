package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.LeaveWord;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:18
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface LeaveWordDao {
    @Select("select * from leaveWord where title = #{title}")
    News selectLeaveWordByTitle(String title);

    @Insert("insert into leaveWord (user_id,job_id,title,content,leave_time) values (#{userId},#{jobId},#{title},#{content},#{leaveTime})")
    void insertLeaveWord(LeaveWord leaveWord);

    @Delete("delete from leaveWord where leaveWord_id = #{leaveWordId}")
    void deleteLeaveWordById(Integer leaveWordId);
    //查询news数据
    @Select("select * from news")
    List<LeaveWord> getLeaveWord();
    //分页排序
    @Select("<script>" +
            "select * from leaveWord "
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
            + " order by leave_time desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<LeaveWord> getLeaveWordBySearchBean(SearchBean searchBean);
}
