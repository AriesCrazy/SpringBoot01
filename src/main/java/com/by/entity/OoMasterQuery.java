package com.by.entity;

import lombok.Data;

import java.time.LocalDate;

/**
* ;
* @author : heimi
* @date : 2025-6-24
*/
@Data
public class OoMasterQuery {
   /** 唯一编号 */
   private String id ;

   private LocalDate ooDate ;
}