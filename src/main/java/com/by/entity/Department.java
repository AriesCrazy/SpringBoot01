package com.by.entity;

import com.by.enums.DepartmentStatus;
import lombok.Data;

import java.time.LocalDate;

 /**
 * 部门表;
 * @author : heimi
 * @date : 2025-6-21
 */
@Data
public class Department extends BaseEntity {
    /** 主键ID */
    private Integer id ;
    /** 部门名称 */
    private String name ;
    /** 负责人 */
    private String manager ;
    /** 联系电话 */
    private String phone ;
    /** 状态: 1启用 2禁用 */
    private Integer status ;
    /** 创建时间 */
    private LocalDate createTime ;
     /** 部门说明 */
     private String brief ;

     private String statusX;
     public String getStatusX() {
         DepartmentStatus status = DepartmentStatus.getByCode(this.status);
         if (status != null){
             return status.getMessage();
         }
         return "未定义";
     }

}