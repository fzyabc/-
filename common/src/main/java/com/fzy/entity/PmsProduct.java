package com.fzy.entity;



import java.math.BigDecimal;
import com.fzy.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author zs
 * @since 2024-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PmsProduct extends BaseEntity {

    private static final long serialVersionUID = 1L;
public PmsProduct(String id,Boolean isPublish){
 super(id);
    this.isPublish = isPublish;
}
    /**
     * 商品名称
     */
    private String name;

    /**
     * 展示价格
     */
    private BigDecimal price;

    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 商品相册
     */
    private String album;

    /**
     * spu属性
     */
    private String spu;

    /**
     * sku属性
     */
    private String sku;

    /**
     * 商品详情
     */
    private String description;

    /**
     * 是否上架
     */
    private Boolean isPublish;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 排序
     */
    private Long sort;


}
