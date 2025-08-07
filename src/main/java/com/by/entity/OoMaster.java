package com.by.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * ;
 * @author : heimi
 * @date : 2025-6-24
 */
@Data
public class OoMaster extends BaseEntity {
    /** 唯一编号 */
    private String id ;
    /** 备注 */
    private String brief ;
    /** 出库日期 */
    private LocalDate ooDate ;
    /** 出库人 */
    private String deliverer ;

    private List<OoItem> items;
}