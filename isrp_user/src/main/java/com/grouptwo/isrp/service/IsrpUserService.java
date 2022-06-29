package com.grouptwo.isrp.service;

import com.grouptwo.isrp.entity.IsrpUser;
import com.grouptwo.isrp.pojo.IsrpUserAddPojo;
import com.grouptwo.isrp.pojo.LoginForm;
import com.grouptwo.isrp.pojo.LoginFormPojo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * 用户表(IsrpUser)表服务接口
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
public interface IsrpUserService {

    /**
     * 登录认证授权
     * @param loginFormPojo
     * @param request
     * @return
     */

    ResponseEntity login(LoginFormPojo loginFormPojo, HttpServletRequest request);
    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    IsrpUser queryById(String userId);

    /**
     * 通过邮箱查找用户
     *
     * @param email
     * @return 实例对象
     */
    IsrpUser queryByEmail(String email);

    /**
     * 分页查询
     *
     * @param isrpUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<IsrpUser> queryByPage(IsrpUser isrpUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param isrpUserAddPojo 实例对象
     * @return 实例对象
     */
    IsrpUser insert(IsrpUserAddPojo isrpUserAddPojo);

    /**
     * 修改数据
     *
     * @param isrpUser 实例对象
     * @return 实例对象
     */
    IsrpUser update(IsrpUser isrpUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(String userId);


    Map<String,Object> registerUser(IsrpUser user);

    List<IsrpUser> getAlluser();

    int changeUserStatus(String userId);

    int getUserStatus(String userId);
}
