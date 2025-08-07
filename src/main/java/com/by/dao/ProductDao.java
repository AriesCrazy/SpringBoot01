package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.Product;
import com.by.entity.ProductQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    @PageX
    List<Product> select(ProductQuery query);

    int insert(Product product);

    int update(Product product);

    int updateStatus(Integer id,Integer status);
}
