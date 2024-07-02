package com.fzy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fzy.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PmsCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //添加用 构造方法
    public PmsCategory(String name, String parentId, Integer level) {
        this.name = name;
        this.parentId = parentId;
        this.level = level;
    }

    //修改用 构造方法 注意：只能修改分类名称
    public PmsCategory(String id, String name) {
        super(id);
        this.name = name;
    }

    //删除用 构造方法
    public PmsCategory(String id, Boolean active) {
        super(id);
        this.active = active;
    }

    /**
     * 分类名称
     */
    private String name;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 当前层级
     */
    private Integer level;

    /**
     * 状态
     */
    private Boolean active;

    private Long sort;

    @TableField(exist = false) //该属性在表中不存在映射字段
    private List<PmsCategory> children;


}
