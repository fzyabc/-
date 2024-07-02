package com.fzy.controller;

import com.fzy.entity.PmsAttr;
import com.fzy.entity.PmsCategory;
import com.fzy.service.PmsAttrService;
import com.fzy.service.PmsCategoryService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/pmsAttr")
public class PmsAttrController {

    @Resource
    PmsAttrService pmsAttrService;

    @Resource
    PmsCategoryService pmsCategoryService;

    //提供分类列表（目的：根据分类列表，对分类进行选择，选择分类后，才能够通过分类查询对应的属性列表）
    @GetMapping("/getCategory")
    public ResultJson<List<PmsCategory>> getCategory(){
        return ResultJson.success(pmsCategoryService.getAll());
    }

    /**
     * 查询属性列表
     *
     * 通过分类，查询对应的分类属性列表
     * 分类，有层级关系的，不仅仅是查询子级的分类的属性，上级分类的属性也都需要查询出来
     *
     *  电子
     *      数码
     *          手机
     *
     *
     *
     * @param categoryIds
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/list")
    public ResultJson<List<PmsAttr>> list(String[] categoryIds ) throws InterruptedException {

        return ResultJson.success(pmsAttrService.list(categoryIds));

    }

    @PostMapping("/save")
    public ResultJson<Boolean> save(String name, String categoryId, Integer type) {

        return ResultJson.success(pmsAttrService.save(name, categoryId, type), "添加属性成功");
    }

    @GetMapping("/getById")
    public ResultJson<PmsAttr> getById(String id) {
        return ResultJson.success(pmsAttrService.getById(id));
    }

    @PostMapping("/update")
    public ResultJson<Boolean> update(String id, String name,Integer type) {

        return ResultJson.success(pmsAttrService.update(id, name, type), "修改属性成功");
    }

    @PostMapping("/delete")
    public ResultJson<Boolean> delete(String id) {
        return ResultJson.success(pmsAttrService.delete(id),"删除属性成功");
    }

}
