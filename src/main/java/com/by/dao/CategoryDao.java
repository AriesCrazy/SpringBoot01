package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.Category;
import com.by.entity.CategoryQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * ;(category)表数据库访问层
 * @author : heimi
 * @date : 2025-4-14
 */
@Mapper
public interface CategoryDao{
    @PageX
    List<Category> select(CategoryQuery query);
    Integer insert(Category category);
    Integer insertBatch(List<Category> categorys);
    Integer update(Category category);
    Integer delete(Integer id);
}