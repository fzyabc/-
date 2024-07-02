package com.fzy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fzy.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 角色和用户关联
 * </p>
 *
 * @author fzy
 * @since 2024-06-22
 */
@TableName("ums_role_user")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UmsRoleUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

      private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 用户id
     */
    private String userId;

    private Long sort;
public UmsRoleUser(String roleId, String userId) {
    this.roleId = roleId;
    this.userId = userId;
}
}
