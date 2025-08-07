package com.by.controller;

import com.by.entity.Roles;
import com.by.entity.RolesQuery;
import com.by.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(roles)控制层
 * @author : heimi
 * @date : 2025-6-21
 */
@RestController
@RequestMapping("/api/roles")
public class RolesController{
    @Autowired
    private RolesService rolesService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
     public List<Roles> select(RolesQuery query){
        return rolesService.select(query);
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Integer insert(@RequestBody Roles roles){
       return rolesService.insert(roles);
    }
    
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Integer update(@RequestBody Roles roles){
        return rolesService.update(roles);
    }
    
    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Integer delete(Integer id){
        return rolesService.delete(id);
    }
}