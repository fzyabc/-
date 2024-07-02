package com.fzy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fzy.entity.PmsBrand;
import com.fzy.entity.UmsAdmin;
import com.fzy.service.IPmsBrandService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2024-05-27
 */
@RestController
@RequestMapping("/pmsBrand")
public class PmsBrandController {

    @Resource
    IPmsBrandService pmsBrandService;


    @GetMapping("/list")
    public ResultJson<IPage<PmsBrand>> getPmsBrandList(Integer pageNo, Integer pageSize, String name) throws InterruptedException {

        return ResultJson.success(pmsBrandService.getPmsBrandList(pageNo, pageSize, name));

    }

    @PostMapping("/save")
    public ResultJson<Boolean> save(String name, String description, MultipartFile file) {

        return ResultJson.success(pmsBrandService.save(name, description, file), "添加品牌成功");
    }

    @GetMapping("/getById")
    public ResultJson<PmsBrand> getById(String id) {
        return ResultJson.success(pmsBrandService.getById(id));
    }

    @PostMapping("/update")
    public ResultJson<Boolean> update(String id, String name,String description, MultipartFile file) {

        return ResultJson.success(pmsBrandService.update(id, name, description, file), "修改品牌成功");
    }

    @PostMapping("/delete")
    public ResultJson<Boolean> delete(String id, Boolean active) {
        return ResultJson.success(pmsBrandService.delete(id, active), active ? "启用品牌成功" : "禁用品牌成功");
    }

}


























