package com.fzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzy.entity.UmsRole;
import com.fzy.mapper.UmsRoleMapper;
import com.fzy.service.IUmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author fzy
 * @since 2024-06-22
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

    @Override
    public List<UmsRole> list(String value) {
        QueryWrapper<UmsRole> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(value)){
            queryWrapper.like("name",value);
        }
return this.list(queryWrapper);
    }

    @Override
    public boolean add(String name) {
   return this.save(new UmsRole(name));
    }

    @Override
    public boolean update(String id, String name) {
       return this.updateById(new UmsRole(id,name));
    }

    @Override
    public boolean del(String id, Boolean active) {
       return this.updateById(new UmsRole(id,active));
    }

    @Override
    public boolean check(String value, String id) {
        QueryWrapper<UmsRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",value);
        if (StringUtils.isNotBlank(id)){
            queryWrapper.ne("id",id);
        }
        return this.count(queryWrapper) ==0;
    }


}
