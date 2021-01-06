package com.sfac.geniusdirecruit.modules.backstagesystem.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Role;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.RoleService;
import com.sfac.geniusdirecruit.modules.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.common.entity.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:13
 * 概要：
 * XXXXX
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查看角色信息
     * http://127.0.0.1:8080/role/readRoles-----------get
     */
    @GetMapping("/readRoles")
    public List<Role> selectAllRoles(){
        return roleService.selectAllRoles();
    }

    /**
     *查询所有角色信息并分页
     *  http://127.0.0.1:8080/role/readRoles--------post
     *  {"currentPage":1, "pageSize":2, "order":"role_id", "direction":"desc", "keyWord":""}
     */
    @PostMapping(value = "/readRoles",consumes = "application/json")
    public PageInfo<Role> getRolesBySearchBean(@RequestBody SearchBean searchBean){
        return roleService.getRolesBySearchBean(searchBean);
    }

    /**
     *添加角色
     * http://127.0.0.1:8080/role/addRole--------post
     * {"roleName":"common","roleDescribe":"普通用户"}
     */
    @GetMapping(value = "/addRole",consumes = "application/json")
    public ResultEntity<Role> insertRole(@RequestBody Role role){
        return roleService.insertRole(role);
    }

    /**
     * 通过roleId查询用户,预编辑
     * http://127.0.0.1:8080/api/user/1---------get
     */
    /*@GetMapping("/role/{roleId}")
    public Role getRoleByRoleId(@PathVariable int roleId) {

    }*/
}
