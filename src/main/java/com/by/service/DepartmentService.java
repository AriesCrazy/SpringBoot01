package com.by.service;

import com.by.dao.DepartmentDao;
import com.by.entity.Department;
import com.by.entity.DepartmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门表;(department)表服务实现类
 * @author : heimi
 * @date : 2025-6-21
 */
@Service
public class DepartmentService{
     
    @Autowired
    private DepartmentDao departmentDao;
    
    public List<Department> select(DepartmentQuery query) {
        return departmentDao.select(query);
    }
    
    public Integer insert(Department department) {
        return departmentDao.insert(department);
    }
    
    public Integer update(Department department) {
        return departmentDao.update(department);
    }
    
    public Integer delete(Integer id) {
        return departmentDao.delete(id);
    }
    
}