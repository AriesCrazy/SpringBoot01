package com.by.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * ;
 * @author : heimi
 * @date : 2025-6-18
 */
@Data
public class IoMaster extends BaseEntity {
    /** 唯一编号 */
    private String id ;
    /** 供应商编号 */
    private Integer vendorId ;
    /** 供应商名称 */
    private String vendorName ;
    /** 备注 */
    private String brief ;
    /** 采购单号 */
    private String poId ;
    /** 入库日期 */
    private LocalDate ioDate ;
    /** 入库人 */
    private String receiver ;

    private List<IoItem> items;
}