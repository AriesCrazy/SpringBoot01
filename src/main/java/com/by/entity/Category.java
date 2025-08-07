package com.by.entity;

import com.by.enums.CategoryStatus;
import lombok.Data;

/**
 * ;
 *
 * @author : heimi
 * @date : 2025-4-14
 */
@Data
public class Category extends BaseEntity {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 父类id
     */
    private Integer parentId;

    private String parentName;
    /**
     * 类目图片
     */
    private String img;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer seq;

    private String statusX;

    public String getStatusX() {
        CategoryStatus categoryStatus = CategoryStatus.getByCode(this.getStatus());
        if (categoryStatus != null) return categoryStatus.getMessage();
        return "未定义";
    }
}