package com.by.entity;

import lombok.Data;

import java.time.LocalDate;

/**
* ;
* @author : heimi
* @date : 2025-6-18
*/
@Data
public class IoMasterQuery{
   /** 唯一编号 */
   private String id ;
   /** 供应商编号 */
   private Integer vendorId ;
   /** 采购单号 */
   private String poId ;
}