package com.by.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * ;
 * @author : heimi
 * @date : 2025-6-12
 */
@Data
public class PoMasterQuery {
    /** 唯一编号 */
    private String id ;
    /** 供应商编号 */
    private Integer vendorId ;

    private String vendorName ;
    /** 采购日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate poDate ;
    /** 状态 */
    private Integer status ;
    /** 采购人 */
    private String purchaser ;
}