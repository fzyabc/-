package com.fzy.entity;

import com.fzy.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PmsAttr extends BaseEntity {

    //添加用构造方法
    public PmsAttr(String name, String categoryId, Integer type) {
        this.name = name;
        this.categoryId = categoryId;
        this.type = type;
    }

    //修改用构造方法  id手动set
    public PmsAttr(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 1-spu 0-sku
     */
    private Integer type;

    /**
     * 排序
     */
    private Long sort;


}
