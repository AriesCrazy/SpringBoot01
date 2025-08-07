package com.by.entity;

import com.by.enums.VendorStatus;
import lombok.Data;

import java.util.List;

/**
 * ;
 * @author : heimi
 * @date : 2025-6-6
 */
@Data
public class Vendor extends BaseEntity {
    /** 唯一编号 */
    private Integer id ;
    /** 供应商名称 */
    private String name ;
    /** 邮箱 */
    private String email ;
    /** 图片 */
    private List<String> img ;
    /** 电话 */
    private String tel ;
    /** 联系人 */
    private String contact ;
    /** 状态 */
    private Integer status ;
    /** 详情 */
    private String brief ;
    /** 省 */
    private String province ;
    /** 市 */
    private String city ;
    /** 区/县 */
    private String county ;
    /** 详细地址 */
    private String address ;
    private Double lat;
    private Double lng;
    /** 排序 */
    private Integer seq ;

    private String statusX;
    public String getStatusX() {
        VendorStatus status = VendorStatus.getByCode(this.status);
        if (status != null){
            return status.getMessage();
        }
        return "未定义";
    }
}