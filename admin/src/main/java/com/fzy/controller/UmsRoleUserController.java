package com.fzy.controller;

import com.fzy.service.IUmsRoleUserService;
import com.fzy.service.UmsAdminService;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色和用户关联 前端控制器
 * </p>
 *
 * @author fzy
 * @since 2024-06-22
 */
@RestController
@RequestMapping("/umsRoleUser")
public class UmsRoleUserController {
    @Resource
    UmsAdminService umsAdminService;
    @Resource
    IUmsRoleUserService umsRoleUserService;
@GetMapping("/init")
    public ResultJson<Map> init(String roleId)
{
    Map<String,List> map=new HashMap<>();
map.put("users",umsAdminService.getActive());
map.put("values",umsRoleUserService.getUserIdByRoleId(roleId));
    return ResultJson.success(map);
}
@PostMapping("/save")
    public ResultJson<Boolean> save(String roleId,String[] values){

return ResultJson.success(umsRoleUserService.save(roleId,values),"关联成功");
}
}
