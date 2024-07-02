package com.fzy.service;

import com.fzy.entity.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author fzy
 * @since 2024-06-22
 */
public interface IUmsRoleService extends IService<UmsRole> {
List<UmsRole> list(String value);
boolean add(String name);
boolean update(String id,String name);
boolean del(String id,Boolean active);
boolean check(String value,String id);

}
