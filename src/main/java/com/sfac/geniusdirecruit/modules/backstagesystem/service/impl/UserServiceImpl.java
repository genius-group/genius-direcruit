package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.common.utile.MD5Util;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.CompanyDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobhunterDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserRoleDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Company;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Jobhunter;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.UserRole;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:30
 * 概要：
 * XXXXX
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private JobhunterDao jobhunterDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public PageInfo<User> getUsersBySearchBean(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(userDao.getUsersBySearchBean(searchBean))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public ResultEntity<User> insertUser(User user) {
        userDao.insertUser(user);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "insert success",user);
    }

    @Override
    public User getUserById(int userId) {
        return  userDao.getUserById(userId);
    }

    @Override
    public ResultEntity<User> editUser(User user) {
        userDao.editUser(user);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "update success",user);
    }

    @Override
    public ResultEntity<Object> deleteUserByUserId(Integer userId) {
        Jobhunter jobHunter=jobhunterDao.selectJobHunterByUserId(userId);
        if (jobHunter!=null){
            jobhunterDao.deleteJobHunterByUserId(userId);
        }else{
            Company company=companyDao.selectCompanyByUserId(userId);
            if (company!=null){
                companyDao.deleteCompanyByUserId(userId);
            }
        }
        List<UserRole> userRoles=userRoleDao.selectByUserId(userId);
        if (userRoles.size()!=0){
            userRoleDao.deleteByUserId(userId);
        }
        userDao.deleteUserByUserId(userId);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "delete success");
    }

    @Override
    public HashMap<Object, String> loginIn(User user) {
        HashMap<Object,String> map = new HashMap<Object, String>();
        User user_db = userDao.selectUserByUserName(user.getUserName());
        if (user_db==null){
            map.put("info","用户不存在");
        }else {
            if (user.getUserPwd().equals(user_db.getUserPwd())){
                map.put("info","登录成功");
                return map;
            }else {
                map.put("info","密码错误");
                return map;
            }
        }
        return map;
    }

    @Override
    public ResultEntity<User> login(User user) {
        try {
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(user.getUserName(), MD5Util.getMD5(user.getUserPwd()));
//			usernamePasswordToken.setRememberMe(user.getRememberMe());

            subject.login(usernamePasswordToken);
            subject.checkRoles();

            Session session = subject.getSession();
            User userTemp = (User) subject.getPrincipal();
            session.setAttribute("userId", userTemp.getUserId());
            session.setAttribute("userName", userTemp.getUserName());

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultEntity<User>(ResultEntity.ResultStatus.SUCCESS.status, "User name or password error.");
        }

        return new ResultEntity<User>(ResultEntity.ResultStatus.SUCCESS.status, "Login success.", user);
    }

    @Override
    public User selectUserByUserName(String userName) {
        return userDao.selectUserByUserName(userName);
    }


    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        session.removeAttribute("userId");
    }
}
