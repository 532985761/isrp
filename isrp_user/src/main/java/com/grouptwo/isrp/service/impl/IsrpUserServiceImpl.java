package com.grouptwo.isrp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.grouptwo.isrp.client.AuthClient;
import com.grouptwo.isrp.client.UserClient;
import com.grouptwo.isrp.dao.IsrpUserDao;
import com.grouptwo.isrp.entity.IsrpUser;
import com.grouptwo.isrp.pojo.LoginForm;
import com.grouptwo.isrp.pojo.LoginFormPojo;
import com.grouptwo.isrp.service.IsrpUserService;
import com.grouptwo.isrp.utils.MailSend;
import com.grouptwo.isrp.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.annotation.Resource;
import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户表(IsrpUser)表服务实现类
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@Service("isrpUserService")
public class IsrpUserServiceImpl implements IsrpUserService {
    @Resource
    private IsrpUserDao isrpUserDao;

    @Resource
    private AuthClient authClient;

    @Resource
    private MailSend mailClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private UserClient userClient;

    /**
     * 登录认证授权
     * @param loginFormPojo
     * @return
     */
    @Override
    public ResponseEntity login(LoginFormPojo loginFormPojo, HttpServletRequest request) {
        loginFormPojo.setIp(request.getRemoteAddr());
        Map result = authClient.authentication(loginFormPojo);
        JSONObject resObject = JSON.parseObject(JSON.toJSONString(result));
        Integer status = Integer.valueOf(String.valueOf(resObject.get("status")));
        if(status == 200) {
            return ResponseEntity.ok(resObject.get("data"));
        }
        return new ResponseEntity(resObject.get("message"), HttpStatus.valueOf(status));
    }


    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public IsrpUser queryById(String userId) {
        IsrpUser user = this.isrpUserDao.queryById(userId);
        user.setPassword(null);
        return user;
    }

    /**
     * 通过邮箱查找用户
     *
     * @param email
     * @return 实例对象
     */
    @Override
    public IsrpUser queryByEmail(String email) {
        return this.isrpUserDao.queryByEmail(email);
    }


    /**
     * 分页查询
     *
     * @param isrpUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<IsrpUser> queryByPage(IsrpUser isrpUser, PageRequest pageRequest) {
        long total = this.isrpUserDao.count(isrpUser);
        return new PageImpl<>(this.isrpUserDao.queryAllByLimit(isrpUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param isrpUser 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpUser insert(IsrpUser isrpUser) {
        this.isrpUserDao.insert(isrpUser);
        return isrpUser;
    }

    /**
     * 修改数据
     *
     * @param isrpUser 实例对象
     * @return 实例对象
     */
    @Override
    public IsrpUser update(IsrpUser isrpUser) {
        this.isrpUserDao.update(isrpUser);
        return this.queryById(isrpUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userId) {
        return this.isrpUserDao.deleteById(userId) > 0;
    }

    @Override
    public Map<String, Object> registerUser(IsrpUser user) {
        Map<String,Object> map = new HashMap<>();
        if (user == null){
            throw new IllegalArgumentException("用户参数不能为空!");
        }
        if (StrUtil.isBlank(user.getEmail())){
            map.put("Msg","邮箱不能为空");
            return map;
        }
        if (userClient.queryByEmail(user.getEmail()) != null){
            map.put("Msg","邮箱已经存在，请重新输入！");
            return map;
        }
        if (StrUtil.isBlank(user.getPassword())){
            map.put("Msg","密码不能为空");
            return map;
        }
        if (user.getRole() == null){
            map.put("Msg","角色不能为空");
            return map;
        }
        if (user.getAddressCity() == null){
            map.put("Msg","城市未选择");
            return map;
        }
        IsrpUser u= isrpUserDao.queryByEmail(user.getEmail());
        if (u != null){
            map.put("emailMsg",true);
            return map;
        }
        user.setNickname(user.getEmail());
        user.setHeaderImg("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        user.setPhone("未知");
        user.setStatus(0);
        user.setSex(0);
        user.setSign("未知");
        user.setUserId(String.valueOf(new SnowflakeIdWorker(0L,0L).nextId()));
        isrpUserDao.insert(user);

        // 激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/bbs/activation/id/code
        String url = "http://localhost:9527/isrpUser/activation/" +user.getUserId();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);
        return map;
    }

    @Override
    public List<IsrpUser> getAlluser() {
        return isrpUserDao.getAlluser();
    }

    @Override
    public int changeUserStatus(String userId) {
        return isrpUserDao.changeStatus(userId);
    }

    @Override
    public int getUserStatus(String userId) {
        return isrpUserDao.getuserStatusByUserId(userId);
    }
}
