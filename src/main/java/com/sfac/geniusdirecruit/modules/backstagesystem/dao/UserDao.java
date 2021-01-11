package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.common.entity.SearchBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:19
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface UserDao {

    //用户名密码登录
    @Select("select * from user where user_name = #{userName} and user_pwd =#{userPwd}")
    User selectUserByUserNameAndPwd(String userName,String userPwd);

    @Select("select * from user")
    List<User> selectAllUser();

    @Select("<script>" +
            "select * from user "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (user_name like '%${keyWord}%') "
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
    List<User> getUsersBySearchBean(SearchBean searchBean);

    @Insert("insert into user (user_name,user_pwd,create_time,tel,state) values (#{userName},#{userPwd},#{createTime},#{tel},#{state})")
    void insertUser(User user);

    @Select("select * from user where user_id = #{userId}")
    User getUserById(int userId);

    @Update("update user set user_name = #{userName},user_pwd=#{userPwd}, create_time = #{createTime},tel = #{tel},state = #{state} where user_id = #{userId}")
    void editUser(User user);

    @Delete("delete from user where user_id = #{userId}")
    void deleteUserByUserId(Integer userId);

    @Select("select * from user where user_name = #{userName} or tel = #{userName}")
    User selectUserByUserName(String userName);

    @Select("SELECT\n" +
            "jobhunter.job_hunter_id,\n" +
            "jobhunter.user_id,\n" +
            "jobhunter.job_hunter_name,\n" +
            "jobhunter.sex,\n" +
            "jobhunter.birth,\n" +
            "jobhunter.photo,\n" +
            "jobhunter.educate,\n" +
            "jobhunter.email,\n" +
            "jobhunter.address\n" +
            "FROM\n" +
            "`user`\n" +
            "INNER JOIN jobhunter ON `user`.user_id = jobhunter.user_id\n" +
            "where email = #{email}")
    User selectUserByEmail(String email);
}
