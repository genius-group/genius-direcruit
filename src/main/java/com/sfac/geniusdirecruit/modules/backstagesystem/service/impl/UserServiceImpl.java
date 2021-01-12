package com.sfac.geniusdirecruit.modules.backstagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.geniusdirecruit.common.entity.ResultEntity;
import com.sfac.geniusdirecruit.common.entity.SearchBean;
import com.sfac.geniusdirecruit.common.utile.EmailSend;
import com.sfac.geniusdirecruit.common.utile.MD5Util;
import com.sfac.geniusdirecruit.common.utile.SmsSend;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.CompanyDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.JobhunterDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.dao.UserRoleDao;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Company;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.Jobhunter;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.UserRole;
import com.sfac.geniusdirecruit.modules.backstagesystem.entity.vo.UserVo;
import com.sfac.geniusdirecruit.modules.backstagesystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    @Autowired
    private SmsSend smsSend;

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
    public ResultEntity<User> insertUser(UserVo userVo,HttpServletRequest request) {
        User user = new User();
        user.setUserPwd(userVo.getUserPwd());
        user.setUserName(userVo.getUserName());
        user.setTel(userVo.getTel());
        user.setState(1);
        User userTemp = userDao.selectUserByUserName(user.getUserName());
        User userTemp1 = userDao.findUsersByTel(userVo.getTel());
        String sessionCode = request.getSession().getAttribute("smsCode")+"";
        if (userTemp1 != null) {
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "tel is registered.", user);
        }
        if (sessionCode.equals(userVo.getCode())) {
            if (userTemp != null) {
                return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                        "User name is repeat.", user);
            }
            user.setUserPwd(MD5Util.getMD5(user.getUserPwd()));
            user.setCreateTime(LocalDateTime.now());
            userDao.insertUser(user);
            // 管理员编辑用户信息时，只修改用户角色
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "register success", user);

        }
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "code is error.", user);
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


    //求职者的注册
    @Override
    public HashMap<Object, String> registerStaff(User user) {

        HashMap<Object, String> map = new HashMap<Object, String>();


        User userTemp = userDao.findUsersByUsername(user.getUserName());

        if (userTemp !=null) {
            map.put("info","该用户名已被注册，请重新输入");
        }else {
            //设置时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime createTime = LocalDateTime.parse(format, df);
            user.setCreateTime(createTime);

            //设置状态
            user.setState(1);
            userDao.insertRegisterUser(user);

            //操作中间表
            user.getRoles().stream().forEach(item -> {

                UserRole userRole = new UserRole(user.getUserId(), item.getRoleId());
                userRoleDao.insertRegisterUser(userRole);

            });


        /*if (user.getRoles() != null) {
            for(Role role : user.getRoles()) {
                UserRole userRole = new UserRole(user.getUserId(), role.getRoleId());
                userRoleDao.insertRegisterUser(userRole);
            }
        }*/

            map.put("info","注册成功");

        }

        return map;

    }

    //发送短信
    @Override
    public HashMap<String, Object> sendSms(String phone, HttpServletRequest request) {

        HashMap<String,Object> map = new HashMap<String,Object>();

        Random rd = new Random();
        int code = rd.nextInt(1000);
        //发送短信
        String info = smsSend.send(phone,code+"");
        //判断是否成功
        if(info.equals("OK")){
            //存入session中
            request.getSession().setAttribute("smsCode",code);
            map.put("info","短信发送成功");
        }else{
            map.put("info","短信发送失败");
        }

//        request.getSession().setAttribute("smsCode",1111);

        return map;
    }

    //手机短信进入
    @Override
    public HashMap<String, Object> smsEnter(UserVo userVo, HttpServletRequest request) {


                HashMap<String,Object> map = new HashMap<String,Object>();

        String sessionCode = request.getSession().getAttribute("smsCode")+"";

        if(userVo.getCode().equals(sessionCode)){

            System.err.println("smsEnter>>>>>>>>>>>>>>>"+userVo.getTel());

            System.err.println("smsEnter>>>>>>>>>>>>>>>"+userVo.getCode());


            //根据电话去查询用户，如果已注册，则会去登录页面
            User userTemp = userDao.selectUsersByTel(userVo.getTel());
            if (userTemp!=null){
                map.put("info","用户已注册，请登录");
            }else {

                map.put("info","登入成功");
            }

        }else{
            map.put("info","登入失败");
        }

        return map;

    }
    @Override
    public HashMap<String, Object> emailLogin(String email, Integer code, HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        Integer code0 = (Integer) request.getSession().getAttribute("code");
        System.err.println(code0);
        String email0 = (String) request.getSession().getAttribute("email");
        System.err.println(email0);
        if (code.equals(code0)&&email.equals(email0)){
            map.put("info","登录成功");
            return map;
        }else {
            map.put("info","登录失败，请稍后重试");
            return map;
        }
    }

    @Override
    public HashMap<String, Object> sendMessage(String tel, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();

            int code = (int)((Math.random()*9+1)*1000);
            String info = smsSend.send(tel,code+"");
            System.err.println(info);
            if (info.equals("OK")){
                //存入session中
                request.getSession().setAttribute("smsCode",code);
                request.getSession().setAttribute("tel",tel);
                map.put("info","短信发送成功");
                return map;
            }else{
                map.put("info","短信发送失败");
                return map;
            }
    }

    @Override
    public HashMap<String, Object> messageLogin(String tel, Integer code, HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        Integer code0 = (Integer) request.getSession().getAttribute("smsCode");
        String tel0 = (String) request.getSession().getAttribute("tel");
        if (code.equals(code0)&&tel.equals(tel0)){
            map.put("info","登录成功");
            return map;
        }else {
            map.put("info","登录失败，请稍后重试");
            return map;
        }
    }






}
