package com.by.entity;

import lombok.Data;

 /**
 * ;
 * @author : heimi
 * @date : 2025-6-24
 */
@Data
public class OoItem extends BaseEntity {
    /** 唯一编号 */
    private Integer id ;
    /** 出库单号 */
    private String ooId ;
    /** 商品编号 */
    private Integer productId ;
    /** 商品名称 */
    private String productName ;
    /** 实际出库数量 */
    private Integer qty ;
    /** 备注 */
    private String brief ;
}