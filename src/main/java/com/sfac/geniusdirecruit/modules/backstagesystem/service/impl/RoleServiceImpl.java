package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.RoleDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Role;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.RoleService;
import com.sfac.geniusdirecruit.modules.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.common.entity.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:29
 * 概要：
 * XXXXX
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> selectAllRoles() {
        return roleDao.selectAllRoles();
    }

    @Override
    public PageInfo<Role> getRolesBySearchBean(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(roleDao.getRolesBySearchBean(searchBean))
                .orElse(Collections.emptyList()));
    }

    @Override
    public ResultEntity<Role> insertRole(Role role) {
        roleDao.insertRole(role);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "insert success",role);
    }
}
