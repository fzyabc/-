package com.fzy.service;

import com.fzy.entity.UmsRoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色关联权限 服务类
 * </p>
 *
 * @author fzy
 * @since 2024-06-24
 */
public interface IUmsRoleResourceService extends IService<UmsRoleResource> {
boolean save(String roleId,String[] menus,String[] buttons);
List<String> getResourceIdsByRoleId(String roleId);
}
