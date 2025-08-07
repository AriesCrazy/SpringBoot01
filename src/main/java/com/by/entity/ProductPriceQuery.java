package com.by.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductPriceQuery {
    private Integer productId;
    private Integer status;
}
