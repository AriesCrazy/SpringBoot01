package com.by.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.by.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductPlusDao extends BaseMapper<Product> {

}
