package com.by.entity;

import lombok.Data;

import java.util.List;

/**
 * ;
 * @author : heimi
 * @date : 2025-6-6
 */
@Data
public class VendorQuery{
    /** 唯一编号 */
    private Integer id ;
    /** 供应商名称 */
    private String name ;
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
}