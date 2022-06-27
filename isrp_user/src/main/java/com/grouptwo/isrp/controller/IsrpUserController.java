package com.grouptwo.isrp.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.server.HttpServerRequest;
import com.grouptwo.isrp.annotation.RolesAuthorization;
import com.grouptwo.isrp.entity.IsrpUser;
import com.grouptwo.isrp.pojo.LoginForm;
import com.grouptwo.isrp.pojo.LoginFormPojo;
import com.grouptwo.isrp.service.IsrpUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户表(IsrpUser)表控制层
 *
 * @author makejava
 * @since 2022-06-19 19:08:33
 */
@RestController
@RequestMapping("isrpUser")
public class IsrpUserController {



    /**
     * 服务对象
     */
    @Resource
    private IsrpUserService isrpUserService;

    /**
     * 用户登录
     *
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("/userLogin")
    public ResponseEntity userLogin(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        LoginFormPojo loginFormPojo = new LoginFormPojo();
        BeanUtil.copyProperties(loginForm, loginFormPojo);
        loginFormPojo.setRole(0);
        return isrpUserService.login(loginFormPojo, request);
    }

    /**
     * 商家登录
     *
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("/businessLogin")
    public ResponseEntity businessLogin(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        LoginFormPojo loginFormPojo = new LoginFormPojo();
        BeanUtil.copyProperties(loginForm, loginFormPojo);
        loginFormPojo.setRole(1);
        return isrpUserService.login(loginFormPojo, request);
    }

    /**
     * 管理员登录
     *
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("/managerLogin")
    public ResponseEntity managerLogin(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        LoginFormPojo loginFormPojo = new LoginFormPojo();
        BeanUtil.copyProperties(loginForm, loginFormPojo);
        loginFormPojo.setRole(2);
        return isrpUserService.login(loginFormPojo, request);
    }

    /**
     * 分页查询所有信息
     *
     * @param page
     * @param size
     * @return
     */
    @RolesAuthorization(value = {"manager"})
    @GetMapping("/queryByPage/{page}/{size}")
    public ResponseEntity<Page<IsrpUser>> queryByPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        IsrpUser isrpUser = new IsrpUser();
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(this.isrpUserService.queryByPage(isrpUser, pageRequest));
    }

    /**
     * 通过ID查询用户信息
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("{id}")
    public ResponseEntity<IsrpUser> queryByIdq(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.isrpUserService.queryById(id));
    }
    /**
     * 通过ID查询用户信息
     *
     * @param userId 主键
     * @return 单条数据
     */
    @PostMapping("/getUserInfo")
    public ResponseEntity<IsrpUser> queryById(String userId) {
        return ResponseEntity.ok(this.isrpUserService.queryById(userId));
    }

    /**
     * 通过email查询单条数据
     * 只能用于内部调用
     *
     * @param email 主键
     * @return 单条数据
     */
    @GetMapping("/emailInner/{email}")
    public IsrpUser queryByEmailInner(@PathVariable("email") String email) {
        return this.isrpUserService.queryByEmail(email);
    }

    /**
     * 通过email查询单条数据
     * 用于外部调用
     *
     * @param email 主键
     * @return 单条数据
     */
    @RolesAuthorization(value = {"manager"})
    @GetMapping("/email/{email}")
    public ResponseEntity<IsrpUser> queryByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(queryByEmailInner(email));
    }

    /**
     * 新增数据
     *
     * @param isrpUser 实体
     * @return 新增结果
     */
    @RolesAuthorization(value = {"manager"})
    @PostMapping("/add")
    public ResponseEntity<IsrpUser> add(IsrpUser isrpUser) {
        return ResponseEntity.ok(this.isrpUserService.insert(isrpUser));
    }

    /**
     * 编辑数据
     *
     * @param isrpUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IsrpUser> edit(IsrpUser isrpUser) {
        return ResponseEntity.ok(this.isrpUserService.update(isrpUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @RolesAuthorization(value = {"manager"})
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.isrpUserService.deleteById(id));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody  IsrpUser user, HttpServletRequest request){
        System.out.println(request);
        Map<String,Object> map = isrpUserService.registerUser(user);
        if (map == null || map.isEmpty()) {
            return new ResponseEntity("已经向你邮箱发送了邮件，请激活账号！", HttpStatus.OK);
        } else {
            return  new ResponseEntity(map.get("Msg"), HttpStatus.SERVICE_UNAVAILABLE);
        }

    }



    @PostMapping("/getAllUser")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(isrpUserService.getAlluser());
    }

}

