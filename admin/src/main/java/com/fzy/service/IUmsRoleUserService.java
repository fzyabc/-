package com.fzy.service;

import com.fzy.entity.UmsRole;
import com.fzy.entity.UmsRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色和用户关联 服务类
 * </p>
 *
 * @author fzy
 * @since 2024-06-22
 */
public interface IUmsRoleUserService extends IService<UmsRoleUser> {
boolean save(String roleId, String[] values);
List<String> getUserIdByRoleId(String roleId);
}
