package com.by.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.by.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("product")
public class Product extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String subName;
    private Integer status;
    @TableField(exist = false)
    private String statusX;
    private String brief;
    private List<String> img;
    private List<String> tags;
    private Integer categoryId;
    private Integer seq;

    public String getStatusX() {
        ProductStatus productStatus = ProductStatus.getByCode(this.status);
        if (productStatus != null){
            return productStatus.getMessage();
        }
        return "未定义";
    }
}
