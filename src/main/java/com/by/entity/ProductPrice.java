package com.by.entity;

import com.by.enums.PriceStatus;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品价格;
 * @author : heimi
 * @date : 2025-4-17
 */
@Data
public class ProductPrice extends BaseEntity {
    /** 唯一编号 */
    private Integer id ;
    /** 商品ID */
    private Integer productId ;
    /** 价格 */
    private BigDecimal price ;
    /** 状态 */
    private Integer status ;

    private String brief;

    private String statusX;

     public String getStatusX() {
         PriceStatus priceStatus = PriceStatus.getByCode(this.status);
         if (priceStatus != null){
             return priceStatus.getMessage();
         }
         return "未定义";
     }
}