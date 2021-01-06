package com.sfac.geniusdirecruit.modules.backstagesystem.service;

import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Role;
import com.sfac.geniusdirecruit.modules.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.common.entity.SearchBean;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:29
 * 概要：
 * XXXXX
 */
public interface RoleService {
    List<Role> selectAllRoles();

    PageInfo<Role> getRolesBySearchBean(SearchBean searchBean);

    ResultEntity<Role> insertRole(Role role);
}
