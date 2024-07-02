package com.fzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzy.entity.PmsCategory;

import java.util.List;

public interface PmsCategoryService extends IService<PmsCategory> {
    List<PmsCategory> getAll();
    Boolean save(String name, String parentId, Integer level);
    Boolean update(String id, String name);
    Boolean delete(String id, Boolean active);

    String getNameByIds(String[] categoryId);
}
