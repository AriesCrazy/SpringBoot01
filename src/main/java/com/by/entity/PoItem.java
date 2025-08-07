package com.by.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ;
 * @author : heimi
 * @date : 2025-6-12
 */
@Data
public class PoItem extends BaseEntity {
    /** 唯一编号 */
    private Integer id ;
    /** 采购单号 */
    private String poId ;
    /** 商品编号 */
    private Integer productId ;
    /** 商品名称 */
    private String productName ;
    private String img ;
    /** 采购数量 */
    private Integer qty ;
    /** 价格 */
    private BigDecimal price ;
    /** 备注 */
    private String brief ;
}