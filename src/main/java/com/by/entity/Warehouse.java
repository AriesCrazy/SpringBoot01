package com.by.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.by.enums.WarehouseStatus;
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
@TableName(value = "warehouse",autoResultMap = true)
public class Warehouse extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer status;
    @TableField(exist = false)
    private String statusX;
    private String brief;
    //@TableField(typeHandler = JacksonTypeHandler.class)
    @TableField(typeHandler = com.by.typehandler.JsonTypeHandler.class)
    private List<String> img;
    private Integer seq;

    public String getStatusX() {
        WarehouseStatus warehouseStatus = WarehouseStatus.getByCode(this.status);
        if (warehouseStatus != null){
            return warehouseStatus.getMessage();
        }
        return "未定义";
    }
}
