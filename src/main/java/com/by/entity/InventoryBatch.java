package com.by.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

 /**
 * ;
 * @author : heimi
 * @date : 2025-6-23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryBatch extends BaseEntity {
    /** 唯一编号 */
    private Integer id ;
    /** 商品id */
    private String batchId ;
    /** 商品id */
    private Integer productId ;
    /** 商品名称 */
    private String productName ;
    /** 批次库存量 */
    private Integer qty ;
    /** 货架id */
    private Integer shelfId ;
    /** 生产日期 */
    private LocalDate productionDate ;
    /** 保质期-天 */
    private Integer shelfLife ;
    /** 过期日期 */
    private LocalDate expiryDate ;
    /** 备注 */
    private String brief ;

}