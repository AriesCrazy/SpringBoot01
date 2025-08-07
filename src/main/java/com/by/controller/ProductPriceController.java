package com.by.controller;

import com.by.entity.ProductPrice;
import com.by.entity.ProductPriceQuery;
import com.by.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品价格;(product_price)控制层
 *
 * @author : heimi
 * @date : 2025-4-17
 */
@RestController
@RequestMapping("/api/price")
public class ProductPriceController {
    @Autowired
    private ProductPriceService productPriceService;

    /**
     * 查询接口
     */
    @GetMapping
    public List<ProductPrice> select(ProductPriceQuery query) {
        return productPriceService.select(query);
    }

    @PostMapping
    public Integer insert(@RequestBody ProductPrice price) {
        return productPriceService.insert(price);
    }

    @PutMapping
    public Integer update(@RequestBody ProductPrice price) {
        return productPriceService.update(price);
    }

    @DeleteMapping
    public Integer delete(Integer id) {
        return productPriceService.delete(id);
    }
}