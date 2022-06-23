package com.grouptwo.isrp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.grouptwo.isrp.dao.IsrpUserDao;
import com.grouptwo.isrp.entity.IsrpUser;
import com.grouptwo.isrp.service.IsrpUserService;
import com.grouptwo.isrp.utils.MailSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.annotation.Resource;
import java.util.HashMap;
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
    private MailSend mailClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Resource
    private TemplateEngine templateEngine;



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
        if (StrUtil.isBlank(user.getPassword())){
            map.put("Msg","密码不能为空");
            return map;
        }
        if (user.getRole() == null){
            map.put("Msg","角色不能为空");
            return map;
        }
        IsrpUser u= isrpUserDao.queryByEmail(user.getEmail());
        if (u != null){
            map.put("emailMsg",true);
            return map;
        }
        user.setNickname(user.getEmail());

        // 激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/bbs/activation/id/code
        String url = "http://localhost:9527/isrpUser/isrpUser/activation/" +user.getUserId();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);
        return map;
    }
}
