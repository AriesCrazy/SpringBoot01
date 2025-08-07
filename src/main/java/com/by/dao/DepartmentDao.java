package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.Department;
import com.by.entity.DepartmentQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * 部门表;(department)表数据库访问层
 * @author : heimi
 * @date : 2025-6-21
 */
@Mapper
public interface DepartmentDao{
    @PageX
    List<Department> select(DepartmentQuery query);
    Integer insert(Department department);
    Integer insertBatch(List<Department> departments);
    Integer update(Department department);
    Integer delete(Integer id);
}