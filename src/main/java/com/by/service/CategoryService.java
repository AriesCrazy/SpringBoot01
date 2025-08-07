package com.by.service;

import com.by.dao.CategoryDao;
import com.by.entity.Category;
import com.by.entity.CategoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ;(category)表服务实现类
 * @author : heimi
 * @date : 2025-4-14
 */
@Service
public class CategoryService{
     
    @Autowired
    private CategoryDao categoryDao;
    
    public List<Category> select(CategoryQuery query) {
        return categoryDao.select(query);
    }
    
    public Integer insert(Category category) {
        return categoryDao.insert(category);
    }
    
    public Integer update(Category category) {
        return categoryDao.update(category);
    }
    
    public Integer delete(Integer id) {
        return categoryDao.delete(id);
    }
    
}