package com.by.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentQuery {
    /** 主键ID */
    private Integer id ;
    /** 部门名称 */
    private String name ;
    /** 状态: 1启用 2禁用 */
    private Integer status ;
}
