package com.fzy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fzy.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author fzy
 * @since 2024-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UmsResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public UmsResource(String name, Integer type, Integer level, String parentId, String frontUrl, String backUrl) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.parentId = parentId;
        this.frontUrl = frontUrl;
        this.backUrl = backUrl;
    }

    public UmsResource(String id, String name, Integer type, String frontUrl, String backUrl) {
        super(id);
        this.name = name;
        this.type = type;
        this.frontUrl = frontUrl;
        this.backUrl = backUrl;
    }

    /**
     * 资源名称
     */
    private String name;

    /**
     * 1-目录 0-按钮
     */
    private Integer type;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 前端地址
     */
    private String frontUrl;

    /**
     * 后端地址
     */
    private String backUrl;

    /**
     *  下级
     * */
    @TableField(exist = false)
    private List<UmsResource> children = new ArrayList<>();

}

