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
 * 角色表
 * </p>
 *
 * @author fzy
 * @since 2024-06-22
 */
@TableName("ums_role")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

public class UmsRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

      private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态
     */
    private Boolean active;

    private Long sort;


    public UmsRole(String name) {
        this.name = name;
    }
    public UmsRole(String id, String name) {
     super(id);
        this.name = name;
    }
    public UmsRole(String id, Boolean active) {
     super(id);
        this.active = active;
    }
}
