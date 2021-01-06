package com.sfac.geniusdirecruit.modules.backstagesystem.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import com.sfac.geniusdirecruit.modules.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.common.entity.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:13
 * 概要：
 * XXXXX
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *  查询所有用户
     *  http://127.0.0.1:8080/api/users-----------get
     * @return
     */
    @GetMapping("/users")
    public List<User> selectAllUsers(){
        return userService.selectAllUser();
    }

    /**
     * 查询所有用户并分页
     * http://127.0.0.1:8080/api/users-----------post
     * {"currentPage":1, "pageSize":2, "order":"create_time", "direction":"desc", "keyWord":""}
     */
    @PostMapping(value = "/users", consumes = "application/json")
    public PageInfo<User> getUsersBySearchBean(@RequestBody SearchBean searchBean){
         return userService.getUsersBySearchBean(searchBean);
    }



    /**
     * 添加用户
     * http://127.0.0.1:8080/api/addUser---------post
     *{"userName":"lisi","userPwd":"444","createTime":"2021-01-05 12:30:00","tel":"18398103075","state":1}
     */
    @PostMapping(value = "/addUser",consumes = "application/json")
    public ResultEntity<User> insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /**
     * 通过userId查询用户,预编辑
     * http://127.0.0.1:8080/api/user/1---------get
     */
    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    /**
     * 编辑用户
     *http://127.0.0.1:8080/api/editUser-----------put
     * {"userId":1,"userName":"lisi","userPwd":"111","createTime":"2008-01-05 12:00:00","tel":"13619087415","state":1}
     */
    @PutMapping(value = "/editUser",consumes = "application/json")
    public ResultEntity<User> editUser(@RequestBody User user){
        return userService.editUser(user);
    }

    /**
     * 删除用户
     * http://127.0.0.1:8080/api/deleteUser/6------delete
     */
    @DeleteMapping("/deleteUser/{userId}")
    public ResultEntity<Object> deleteUserByUserId(@PathVariable Integer userId){
        return userService.deleteUserByUserId(userId);
    }
}
