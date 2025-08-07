package com.by.entity;

import com.by.enums.PoStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * ;
 * @author : heimi
 * @date : 2025-6-12
 */
@Data
public class PoMaster extends BaseEntity {
    /** 唯一编号 */
    private String id ;
    /** 供应商编号 */
    private Integer vendorId ;
    /** 供应商名称 */
    private String vendorName ;
    /** 采购日期 */
    private LocalDate poDate ;
    /** 状态 */
    private Integer status ;
    /** 备注 */
    private String brief ;
    /** 采购总价 */
    private BigDecimal totalPrice ;
    /** 采购人 */
    private String purchaser ;

    private List<PoItem> items;

    private String statusX;
    public String getStatusX() {
        PoStatus status = PoStatus.getByCode(this.status);
        if (status != null){
            return status.getMessage();
        }
        return "未定义";
    }
}