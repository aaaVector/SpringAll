package com.example.mybatis.plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatis.plus.entity.Students;
import com.example.mybatis.plus.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Students)表控制层
 *
 * @author makejava
 * @since 2021-04-24 15:52:50
 */
@RestController
@RequestMapping("students")
public class StudentsController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private StudentsService studentsService;

    @GetMapping("/list")
    public R selectAll() {
        return success(this.studentsService.list());
    }

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param students 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Students> page, Students students) {
        return success(this.studentsService.page(page, new QueryWrapper<>(students)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.studentsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param students 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Students students) {
        return success(this.studentsService.save(students));
    }

    /**
     * 修改数据
     *
     * @param students 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Students students) {
        return success(this.studentsService.updateById(students));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.studentsService.removeByIds(idList));
    }
}
