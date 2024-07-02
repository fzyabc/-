package com.fzy.controller;

import com.fzy.entity.UmsRole;
import com.fzy.service.IUmsRoleService;
import com.fzy.service.IUmsRoleUserService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author fzy
 * @since 2024-06-22
 */
@RestController
@RequestMapping("/umsRole")
public class UmsRoleController {
@Resource
    IUmsRoleService umsRoleService;

@GetMapping("/list")
    public ResultJson<List> list(String value)
{
    return ResultJson.success(umsRoleService.list(value));
}
@PostMapping("/add")
    public ResultJson<Boolean> add(String name)
{
    return ResultJson.success(umsRoleService.add(name),"添加角色成功");
}
@PostMapping("/update")
    public ResultJson<Boolean> update(String id,String name)
{
    return ResultJson.success(umsRoleService.update(id,name),"修改角色成功");
}
@PostMapping("/del")
    public ResultJson<Boolean> del(String id,Boolean active)
{

    return ResultJson.success(umsRoleService.del(id,active),active?"启用角色成功":"启用角色成功");

}
@GetMapping("/getById")
    public ResultJson<UmsRole> getById(String id)
{
    return ResultJson.success(umsRoleService.getById(id));
}
@GetMapping("/check")
    public ResultJson<Boolean> check(String value,String id)
{
    return ResultJson.success(umsRoleService.check(value,id));
}
}
