package com.grouptwo.isrp.controller;

import com.grouptwo.isrp.annotation.RolesAuthorization;
import com.grouptwo.isrp.entity.IsrpOrderModel;
import com.grouptwo.isrp.service.IsrpOrderModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 订单模式表(IsrpOrderModel)表控制层
 *
 * @author makejava
 * @since 2022-06-27 10:03:47
 */
@RestController
@RequestMapping("isrpOrderModel")
public class IsrpOrderModelController {
    /**
     * 服务对象
     */
    @Resource
    private IsrpOrderModelService isrpOrderModelService;

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return 查询结果
     */
    @RolesAuthorization(value = {"manager"})
    @GetMapping("/queryByPage/{page}/{size}")
    public ResponseEntity<Page<IsrpOrderModel>> queryByPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        IsrpOrderModel isrpOrderModel = new IsrpOrderModel();
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(this.isrpOrderModelService.queryByPage(isrpOrderModel, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RolesAuthorization(value = {"manager"})
    @GetMapping("{id}")
    public ResponseEntity<IsrpOrderModel> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpOrderModelService.queryById(id));
    }


    /**
     * 新增数据
     *
     *
     * @param orderModelName
     * @return 新增数据
     */
    @RolesAuthorization(value = {"manager"})
    @PostMapping("/add")
    public ResponseEntity<IsrpOrderModel> add(@RequestBody Map<String, String> orderModelName) {
        IsrpOrderModel isrpOrderModel = new IsrpOrderModel();
        isrpOrderModel.setOrderModelName(orderModelName.get("orderModelName"));
        return ResponseEntity.ok(this.isrpOrderModelService.insert(isrpOrderModel));
    }

    /**
     * 编辑数据
     *
     * @param isrpOrderModel 实体
     * @return 编辑结果
     */
    @RolesAuthorization(value = {"manager"})
    @PutMapping("edit")
    public ResponseEntity<IsrpOrderModel> edit(@RequestBody IsrpOrderModel isrpOrderModel) {
        return ResponseEntity.ok(this.isrpOrderModelService.update(isrpOrderModel));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @RolesAuthorization(value = {"manager"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.isrpOrderModelService.deleteById(id));
    }

}

