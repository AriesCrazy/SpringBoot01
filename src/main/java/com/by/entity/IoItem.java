package com.by.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;

 /**
 * ;
 * @author : heimi
 * @date : 2025-6-18
 */
@Data
public class IoItem extends BaseEntity {
    /** 唯一编号 */
    private Integer id ;
    /** 采购单号 */
    private String ioId ;
    /** 商品编号 */
    private Integer productId ;
    /** 商品名称 */
    private String productName ;
    /** 实际入库数量 */
    private Integer qty ;
    /** 货架编号 */
    private Integer shelfId ;
    /** 生产日期 */
    private LocalDate productionDate ;
    /** 保质期(天) */
    private Integer shelfLife ;
    /** 过期日期 */
    private LocalDate expiryDate ;
    /** 备注 */
    private String brief ;
}