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
 * 角色关联权限
 * </p>
 *
 * @author fzy
 * @since 2024-06-24
 */
@TableName("ums_role_resource")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UmsRoleResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 资源类别
     */
    private Integer resourceType;

    private Long sort;


    public UmsRoleResource(String roleId, String resourceId, Integer resourceType) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.resourceType = resourceType;
    }
}