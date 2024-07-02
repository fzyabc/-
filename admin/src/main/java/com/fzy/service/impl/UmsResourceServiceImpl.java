package com.fzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzy.core.FException;
import com.fzy.entity.UmsResource;
import com.fzy.mapper.UmsResourceMapper;
import com.fzy.service.IUmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzy.vo.ResultJson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author fzy
 * @since 2024-06-23
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements IUmsResourceService {

    @Override
    @CacheEvict(value = "ums",key = "'resource'")
    public boolean add(String name, Integer type, Integer level, String parentId, String frontUrl, String backUrl) {
        UmsResource umsResource = new UmsResource(name, type, level, parentId, frontUrl, backUrl);
        return save(umsResource);
    }

    @Override
    @CacheEvict(value = "ums",key = "'resource'")
    public boolean update(String id, String name, Integer type, String frontUrl, String backUrl) {
        UmsResource umsResource = new UmsResource(id, name, type, frontUrl, backUrl);
        return updateById(umsResource);
    }

    @Override
    public boolean check(String id, String name) {
        QueryWrapper<UmsResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        if (StringUtils.isNotBlank(id)) {
            queryWrapper.ne("id", id);
        }
        return count(queryWrapper) == 0;

    }

    @Override
    @Cacheable(value = "ums",key = "'resource'")
    public List<UmsResource> getAll() {
        return this.getByParentId("");
    }

    @Override
    @CacheEvict(value = "ums",key = "'resource'")
    public Boolean del(String id) {
        QueryWrapper<UmsResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        if (this.count(queryWrapper) >0){
            throw new FException("该资源下有子资源，不能删除");
        }
        return this.removeById(id);
    }


    private List<UmsResource> getByParentId(String parentId) {
        QueryWrapper<UmsResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId).orderByAsc("sort");
        List<UmsResource> list = this.list(queryWrapper);
        for (UmsResource umsResource : list) {
            umsResource.setChildren(this.getByParentId(umsResource.getId()));


        }
        return list;
    }
}