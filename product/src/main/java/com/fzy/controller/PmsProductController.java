package com.fzy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fzy.fegin.FileService;
import com.fzy.service.IPmsBrandService;
import com.fzy.service.PmsAttrService;
import com.fzy.service.PmsCategoryService;
import com.fzy.service.PmsProductService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pmsProduct")
public class PmsProductController {
    @Resource
    PmsProductService pmsProductService;
    @Resource
    IPmsBrandService iPmsBrandService;
    @Resource
    PmsCategoryService pmsCategoryService;
    @Resource
    PmsAttrService pmsAttrService;
    @Resource
    FileService fileService;
    @GetMapping("/getData")
    public ResultJson<Map> getData() {
        Map<String, List> map = new HashMap<>();
        map.put("brands", iPmsBrandService.getActive());
        map.put("categories", pmsCategoryService.getAll());
        return ResultJson.success(map);
    }
    @GetMapping("/getAttr")
    public ResultJson<List> getAttr(String[] categoryIds) {
return ResultJson.success(pmsAttrService.list(categoryIds));
    }
    @PostMapping("/upload")
    public ResultJson<String> upload(MultipartFile file) {
        return ResultJson.success(fileService.impageUpload(file));
    }
    @PostMapping("/save")
    public ResultJson<Boolean> save(
            String name,
            BigDecimal price,
            String brandId,
            String[] categoryId,
            String[] spu,
            String[] sku,
            String[] skuArray,
            MultipartFile[] files,
            String description
    ) throws IOException {
        return ResultJson.success(pmsProductService.save(name,price,brandId,categoryId,spu,sku,skuArray,files,description),"保存商品成功");
    }

    @GetMapping("/list")
    public ResultJson<IPage> list(Integer pageNo, Integer pageSize){
        return ResultJson.success(pmsProductService.list(pageNo,pageSize));
    }
@PostMapping("/changeStatus")
    public ResultJson<Boolean> changeStatus(String id,Boolean isPublish){
        return ResultJson.success(pmsProductService.changeStatus(id,isPublish),isPublish?"商品上架成功":"商品下架成功");
    }

}
