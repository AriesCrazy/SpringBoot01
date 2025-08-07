package com.by.entity;

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
public class ProductQuery {
    private Integer id;
    private List<Integer> ids;
    private String name;
    private Integer status;
    private Integer categoryId;
}
