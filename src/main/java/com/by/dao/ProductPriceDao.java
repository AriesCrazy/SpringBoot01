package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.ProductPrice;
import com.by.entity.ProductPriceQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商品价格;(product_price)表数据库访问层
 *
 * @author : heimi
 * @date : 2025-4-17
 */
@Mapper
public interface ProductPriceDao {
    @PageX
    List<ProductPrice> select(ProductPriceQuery query);

    Integer insert(ProductPrice productPrice);

    Integer insertBatch(List<ProductPrice> productPrices);

    Integer update(ProductPrice productPrice);


    //把之前的价格都改为历史价格
    /*<update id="updateOldPriceStatus">
     update product_price
     set status = 0
     where product_id = #{productId}
     and status = 1
    </update>*/
    @Update("update product_price set status = 0 where product_id = #{productId} and status = 1")
    Integer updateOldPriceStatus(Integer productId);


    Integer delete(Integer id);
}