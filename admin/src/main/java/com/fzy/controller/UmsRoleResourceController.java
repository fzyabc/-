package com.fzy.controller;

import com.fzy.service.IUmsResourceService;
import com.fzy.service.IUmsRoleResourceService;
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
 * 角色关联权限 前端控制器
 * </p>
 *
 * @author fzy
 * @since 2024-06-24
 */
@RestController
@RequestMapping("/umsRoleResource")
public class UmsRoleResourceController {
@Resource
    IUmsResourceService umsResourceService;
@Resource
    IUmsRoleResourceService umsRoleResourceService;
@GetMapping("/init")
    public ResultJson<Map> init(String roleId)
    {
        Map<String, List> map=new HashMap<>();
        map.put("resources", umsResourceService.getAll());
        map.put("checks", umsRoleResourceService.getResourceIdsByRoleId(roleId));
        return ResultJson.success(map);
    }
    @PostMapping("/save")
    public ResultJson<Boolean> save(String roleId,String[] menus,String[] buttons)
    {
return ResultJson.success(umsRoleResourceService.save(roleId,menus,buttons),"保存权限成功");
    }
}
