package com.by.controller;

import com.by.entity.Category;
import com.by.entity.CategoryQuery;
import com.by.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(category)控制层
 * @author : heimi
 * @date : 2025-4-14
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController{
    @Autowired
    private CategoryService categoryService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     public List<Category> select(CategoryQuery query){
        return categoryService.select(query);
    }
    
    @PostMapping 
    public Integer insert(@RequestBody Category product){
       return categoryService.insert(product);
    }
    
    @PutMapping 
    public Integer update(@RequestBody Category category){
        return categoryService.update(category);
    }
    
    @DeleteMapping
    public Integer delete(Integer id){
        return categoryService.delete(id);
    }
}