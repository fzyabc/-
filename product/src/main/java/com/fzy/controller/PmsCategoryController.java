package com.fzy.controller;

import com.fzy.entity.PmsCategory;
import com.fzy.service.PmsCategoryService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/pmsCategory")
public class PmsCategoryController {
    @Resource
    PmsCategoryService pmsCategoryService;

    @GetMapping("/list")
    public ResultJson<List<PmsCategory>> getPmsCategoryList() throws InterruptedException {

        return ResultJson.success(pmsCategoryService.getAll());

    }
    @PostMapping("/save")
    public ResultJson<Boolean> save(String name, String parentId, Integer level) {

        return ResultJson.success(pmsCategoryService.save(name, parentId, level), "添加分类成功");
    }
    @GetMapping("/getById")
    public ResultJson<PmsCategory> getById(String id) {
        return ResultJson.success(pmsCategoryService.getById(id));
    }
    @PostMapping("/update")
    public ResultJson<Boolean> update(String id, String name) {

        return ResultJson.success(pmsCategoryService.update(id, name), "修改分类成功");
    }
    @PostMapping("/delete")
    public ResultJson<Boolean> delete(String id, Boolean active) {
        return ResultJson.success(pmsCategoryService.delete(id, active), active ? "启用分类成功" : "禁用分类成功");
    }

}