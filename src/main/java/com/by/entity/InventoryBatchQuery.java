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
@AllArgsConstructor
@NoArgsConstructor
public class InventoryBatchQuery{
   /** 商品id */
   private String batchId ;
   /** 商品id */
   private Integer productId ;
   /** 货架id */
   private Integer shelfId ;
}