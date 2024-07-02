package com.fzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzy.entity.PmsAttr;

import java.util.List;

public interface PmsAttrService extends IService<PmsAttr> {

    List<PmsAttr> list(String[] categoryIds);
    Boolean save(String name, String categoryId, Integer type);
    Boolean update(String id, String name,Integer type);
    Boolean delete(String id);

}
