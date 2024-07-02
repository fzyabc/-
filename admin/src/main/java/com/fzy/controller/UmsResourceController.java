package com.fzy.controller;

import com.fzy.entity.UmsResource;
import com.fzy.service.IUmsResourceService;
import com.fzy.service.IUmsRoleService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author fzy
 * @since 2024-06-23
 */
@RestController
@RequestMapping("/umsResource")
public class UmsResourceController {
@Resource
IUmsResourceService umsResourceService;
@GetMapping("/list")
    public ResultJson<List> list(){
    return ResultJson.success(umsResourceService.getAll());
}
@PostMapping("/add")
    public ResultJson<Boolean> add(String name,Integer type,Integer level,String parentId,String frontUrl,String backUrl){
    return ResultJson.success(umsResourceService.add(name,type,level,parentId,frontUrl,backUrl));
}
@PostMapping("/update")
    public ResultJson<Boolean> update(String id,String name,Integer type,String frontUrl,String backUrl){
    return ResultJson.success(umsResourceService.update(id,name,type,frontUrl,backUrl),"修改资源成功");
    }
    @GetMapping("/check")
    public ResultJson<Boolean> check(String id,String name){
    return ResultJson.success(umsResourceService.check(id,name));
    }
    @GetMapping("/getById")
    public ResultJson<UmsResource> getById(String id){
    return ResultJson.success(umsResourceService.getById(id));
    }
    @PostMapping("/del")
    public ResultJson<Boolean> del(String id,Boolean active){
    return ResultJson.success(umsResourceService.del(id));
    }
}
