package com.by.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ;
* @author : heimi
* @date : 2025-6-23
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryQuery extends BaseEntity {
   /** 商品id，主键 */
   private Integer id ;
   /** 商品名称 */
   private String productName ;
}