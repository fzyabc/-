package com.fzy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fzy.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * sku库存表
 * </p>
 *
 * @author fzy
 * @since 2024-06-16
 */

@TableName("pms_sku_store")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PmsSkuStore extends BaseEntity {

    private static final long serialVersionUID = 1L;

      private String id;

    /**
     * 商品id
     */
    private String productId;

    /**
     * sku组合
     */
    private String skuItem;

    /**
     * 库存数
     */
    private Integer store;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 排序
     */
    private Long sort;


}
