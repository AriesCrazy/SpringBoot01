package com.by.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WarehouseQuery{
    private Integer id;
    private Integer status;
}
