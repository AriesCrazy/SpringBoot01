package com.by.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ;
 * @author : heimi
 * @date : 2025-6-23
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory extends BaseEntity {
    /** 商品id，主键 */
    private Integer id ;
    /** 商品名称 */
    private String productName ;
    /** 库存量，默认0 */
    private Integer qty ;
    /** 销售量，默认0 */
    private Integer saleCount ;
    /** 版本号，用于乐观锁，默认0 */
    private Integer version ;
}