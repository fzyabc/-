package com.fzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzy.core.FException;
import com.fzy.entity.PmsCategory;
import com.fzy.entity.UmsAdmin;
import com.fzy.mapper.PmsCategoryMapper;
import com.fzy.service.PmsCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {
    @Cacheable(value = "pms" , key = "'category'")
   @Override
    public List<PmsCategory> getAll() {

        return this.getByParentId("");
    }
private List<PmsCategory> getByParentId(String parentId) {
        QueryWrapper<PmsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId).orderByAsc("sort");
    //查询指定父ID的List集合 食品 服装
    List<PmsCategory> list = this.list(queryWrapper);
    for (PmsCategory pmsCategory : list){
pmsCategory.setChildren(this.getByParentId(pmsCategory.getId()));
    }
    return list;
    }
    @CacheEvict(value = "pms" , key = "'category'")
    @Override
    public Boolean save(String name, String parentId, Integer level) {
        return this.save(new PmsCategory(name,parentId,level));
    }
    @CacheEvict(value = "pms" , key = "'category'")
    @Override
    public Boolean update(String id, String name) {
        return this.updateById(new PmsCategory(id,name));
    }
    @CacheEvict(value = "pms" , key = "'category'")
    @Override
    public Boolean delete(String id, Boolean active) {
        if (!active && this.getActiveChildrenCount(id) > 0)

            throw new FException("存在未禁用的子类，不能禁用");
        if (active && !this.parentIsActive(id)) {
            throw new FException("上级分类已禁用，请先启用上级分类");

        }
        return this.updateById(new PmsCategory(id,active));
    }

    @Override
    public String getNameByIds(String[] categoryId) {
        List<String> list = new ArrayList<>();
        for (String id : categoryId) {
            String name = this.getById(id).getName();
            list.add(name);
        }
        String names=String.join("/",list);
        return names;
    }

    private int getActiveChildrenCount(String parentId){

        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();

        wrapper.eq("parent_id",parentId);

        return (int) this.count(wrapper);
    }

    /**
     * 判断指定下级id的上级是否禁用
     * @param id
     * @return
     */
    private  boolean parentIsActive(String id){
        String parentId = this.getById(id).getParentId();
        if(StringUtils.isBlank(parentId)){
            return true;
        }
        Boolean active = this.getById(parentId).getActive();
        return active;

    }

}
