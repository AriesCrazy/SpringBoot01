package com.by.controller;

import com.by.entity.Department;
import com.by.entity.DepartmentQuery;
import com.by.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门表;(department)控制层
 * @author : heimi
 * @date : 2025-6-21
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController{
    @Autowired
    private DepartmentService departmentService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
     public List<Department> select(DepartmentQuery query){
        return departmentService.select(query);
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Integer insert(@RequestBody Department department){
       return departmentService.insert(department);
    }
    
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Integer update(@RequestBody Department department){
        return departmentService.update(department);
    }
    
    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Integer delete(Integer id){
        return departmentService.delete(id);
    }
}