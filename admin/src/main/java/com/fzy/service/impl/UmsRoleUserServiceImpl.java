package com.fzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fzy.entity.UmsRoleUser;
import com.fzy.mapper.UmsRoleUserMapper;
import com.fzy.service.IUmsRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 角色和用户关联 服务实现类
 * </p>
 *
 * @author fzy
 * @since 2024-06-22
 */
@Service
public class UmsRoleUserServiceImpl extends ServiceImpl<UmsRoleUserMapper, UmsRoleUser> implements IUmsRoleUserService {

    @Override
    @Transactional
    public boolean save(String roleId, String[] values) {
        this.removeByRoleId(roleId);
        List<UmsRoleUser> list=new ArrayList<>();
        if (null!=values){
            for (String userId:values){
list.add(new UmsRoleUser(roleId,userId));
            }
        }
        return this.saveBatch(list);
    }

    @Override
    public List<String> getUserIdByRoleId(String roleId) {
        QueryWrapper<UmsRoleUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        List<UmsRoleUser> list=this.list(queryWrapper);
        List<String> userIds=new ArrayList<>();
        list.forEach((item)->{
            userIds.add(item.getUserId());
        });
        return userIds;
    }
    private void removeByRoleId(String roleId){
        UpdateWrapper<UmsRoleUser> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("role_id",roleId);
        this.remove(updateWrapper);

    }
}
