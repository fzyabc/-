package com.fzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fzy.entity.UmsRoleResource;
import com.fzy.mapper.UmsRoleResourceMapper;
import com.fzy.service.IUmsRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 * 角色关联权限 服务实现类
 * </p>
 *
 * @author fzy
 * @since 2024-06-24
 */
@Service
public class UmsRoleResourceServiceImpl extends ServiceImpl<UmsRoleResourceMapper, UmsRoleResource> implements IUmsRoleResourceService {
private void removeByRoleId(String roleId){
    UpdateWrapper<UmsRoleResource> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("role_id",roleId);
    this.remove(updateWrapper);
}
    @Override
    @Transactional
    public boolean save(String roleId, String[] menus, String[] buttons) {
    this.removeByRoleId(roleId);
        List<UmsRoleResource> list = new ArrayList<>();
        if (null!=menus){
for (String resourceId:menus){
list.add(new UmsRoleResource(roleId,resourceId,1));
}
        }
        if (null!=buttons){
for (String resourceId:buttons){
list.add(new UmsRoleResource(roleId,resourceId,0));
}
        }
        return this.saveBatch(list);
    }

    @Override
    public List<String> getResourceIdsByRoleId(String roleId) {
        List<String> resourceIds = new ArrayList<>();
        QueryWrapper<UmsRoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId).eq("resource_type",0);

        List<UmsRoleResource> list = this.list(queryWrapper);
        list.forEach(item->{

            resourceIds.add(item.getResourceId());
        });
        return resourceIds;
    }
}
