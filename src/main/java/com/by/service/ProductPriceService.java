package com.by.service;

import com.by.dao.ProductPriceDao;
import com.by.entity.ProductPrice;
import com.by.entity.ProductPriceQuery;
import com.by.enums.PriceStatus;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品价格;(product_price)表服务实现类
 * @author : heimi
 * @date : 2025-4-17
 */
@Service
public class ProductPriceService{
     
    @Autowired
    private ProductPriceDao productPriceDao;
    
    public List<ProductPrice> select(ProductPriceQuery query) {
        return productPriceDao.select(query);
    }

    @SneakyThrows
    @Transactional
    public Integer insert(ProductPrice productPrice) {
        productPriceDao.updateOldPriceStatus(productPrice.getProductId());
        Thread.currentThread().sleep(1000);
        productPrice.setStatus(PriceStatus.NORMAL.getCode());
        return productPriceDao.insert(productPrice);
    }
    
    @SneakyThrows
    @Transactional
    public Integer update(ProductPrice productPrice) {
        productPriceDao.updateOldPriceStatus(productPrice.getProductId());
        Thread.currentThread().sleep(1000);
        productPrice.setStatus(PriceStatus.NORMAL.getCode());
        return productPriceDao.update(productPrice);
    }
    
    public Integer delete(Integer id) {
        return productPriceDao.delete(id);
    }
    
}