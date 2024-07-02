package com.fzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzy.entity.PmsAttr;
import com.fzy.mapper.PmsAttrMapper;
import com.fzy.service.PmsAttrService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr> implements PmsAttrService {

    @Override
    public List<PmsAttr> list(String[] categoryIds) {
        List<PmsAttr> list = new ArrayList<>();
        for (String categoryId : categoryIds) {
            list.addAll(this.getByCategoryId(categoryId));
        }
        return list;
    }

    private List<PmsAttr> getByCategoryId(String categoryId){
        QueryWrapper<PmsAttr> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id",categoryId).orderByAsc("sort");
        return this.list(wrapper);
    }

    @Override
    public Boolean save(String name, String categoryId, Integer type) {
        return this.save(new PmsAttr(name,categoryId,type));
    }

    @Override
    public Boolean update(String id, String name, Integer type) {
        PmsAttr pmsAttr = new PmsAttr(name,type);
        pmsAttr.setId(id);
        return this.updateById(pmsAttr);
    }

    @Override
    public Boolean delete(String id) {
        return this.removeById(id);
    }
}
