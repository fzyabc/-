package com.fzy.entity;


import com.fzy.core.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author zs
 * @since 2024-05-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PmsBrand extends BaseEntity {

    public PmsBrand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PmsBrand(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public PmsBrand(String id, Boolean active) {
        super(id);
        this.active = active;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 品牌详情
     */
    private String description;

    /**
     * 状态
     */
    private Boolean active;

    /**
     * 排序
     */
    private Long sort;


}
