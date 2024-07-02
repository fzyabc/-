package com.fzy.service;

import com.fzy.entity.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzy.vo.ResultJson;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author fzy
 * @since 2024-06-23
 */
public interface IUmsResourceService extends IService<UmsResource> {
boolean add(String name,Integer type,Integer level,String parentId,String frontUrl,String backUrl);

    boolean update(String id, String name, Integer type, String frontUrl, String backUrl);
    boolean check(String id,String name);
    List<UmsResource>  getAll();
    Boolean del(String id);


}
