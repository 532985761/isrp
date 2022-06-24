package com.grouptwo.isrp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.grouptwo.isrp.client.AuthClient;
import com.grouptwo.isrp.dao.IsrpUserDao;
import com.grouptwo.isrp.entity.IsrpUser;
import com.grouptwo.isrp.pojo.LoginForm;
import com.grouptwo.isrp.pojo.LoginFormPojo;
import com.grouptwo.isrp.service.IsrpUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    /**
     * 登录认证授权
     * @param loginForm
     * @return
     */
    @Override
    public ResponseEntity login(LoginForm loginForm, HttpServletRequest request) {
        LoginFormPojo loginFormPojo = new LoginFormPojo();
        BeanUtil.copyProperties(loginForm, loginFormPojo);
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
        return this.isrpUserDao.queryById(userId);
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
}
