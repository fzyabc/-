package com.fzy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fzy.entity.UmsAdmin;
import com.fzy.service.UmsAdminService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2024-05-27
 */
@RestController
@RequestMapping("/umsAdmin")
public class UmsAdminController {

    @Resource
    UmsAdminService umsAdminService;


    @GetMapping("/list")
    public ResultJson<IPage<UmsAdmin>> getUmsAdminList(Integer pageNo, Integer pageSize, String name) throws InterruptedException {


        return ResultJson.success(umsAdminService.getUmsAdminList(pageNo, pageSize, name));

    }


    @PostMapping("/save")
    public ResultJson<Boolean> save(String name, String phone, String email, Integer gender, String password, MultipartFile file) {

        return ResultJson.success(umsAdminService.save(name, phone, email, gender, password, file), "添加管理员成功");
    }

    @GetMapping("/getById")
    public ResultJson<UmsAdmin> getById(String id) {
        return ResultJson.success(umsAdminService.getById(id));
    }

    @PostMapping("/update")
    public ResultJson<Boolean> update(String id, String name, String phone, String email, Integer gender, MultipartFile file) {

        return ResultJson.success(umsAdminService.update(id, name, phone, email, gender, file), "修改管理员成功");
    }

    @PostMapping("/delete")
    public ResultJson<Boolean> delete(String id, Boolean active) {
        return ResultJson.success(umsAdminService.delete(id, active), active ? "启用管理员成功" : "禁用管理员成功");
    }
@PostMapping("/login")
    ResultJson login(String username,String password){
        return ResultJson.success(umsAdminService.login(username,password));
}

}
