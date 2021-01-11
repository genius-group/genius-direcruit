package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.utile.EmailSend;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.CompanyDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobhunterDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserRoleDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Company;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Jobhunter;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.UserRole;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import com.sfac.geniusdirecruit.modules.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.modules.common.entity.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

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
    @Autowired
    private EmailSend emailSend;

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
    public HashMap<String, Object> sendCode(String email, HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        try {
          User user = userDao.selectUserByEmail(email);
            System.err.println(user);
          if (user==null){
            map.put("info","邮箱未注册，请先前往注册页面注册");
            return map;
            }else {
                int code = (int)((Math.random()*9+1)*1000);
                emailSend.send(email,"牛人直聘","您的验证码是："+code+"，请不要告诉他人");
                request.getSession().setAttribute("code",code);
                request.getSession().setAttribute("email",email);
                map.put("info","邮件发送成功，请查收");
                return map;
          }
        }catch (Exception e){
            map.put("info","邮件发送失败");
            return map;
        }
    }

}
