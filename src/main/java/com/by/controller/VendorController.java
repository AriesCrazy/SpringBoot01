package com.by.controller;

import com.by.entity.Vendor;
import com.by.entity.VendorQuery;
import com.by.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(vendor)控制层
 * @author : heimi
 * @date : 2025-6-6
 */
@RestController
@RequestMapping("/api/vendor")
public class VendorController{
    @Autowired
    private VendorService vendorService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     @PreAuthorize("hasAnyRole('ROLE_PURCHASE_MANAGER', 'ROLE_PURCHASE','ROLE_ADMIN')")
     public List<Vendor> select(VendorQuery query){
        return vendorService.select(query);
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_PURCHASE_MANAGER', 'ROLE_PURCHASE','ROLE_ADMIN')")
    public Integer insert(@RequestBody Vendor vendor){
       return vendorService.insert(vendor);
    }
    
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_PURCHASE_MANAGER', 'ROLE_PURCHASE','ROLE_ADMIN')")
    public Integer update(@RequestBody Vendor vendor){
        return vendorService.update(vendor);
    }
    
    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_PURCHASE_MANAGER', 'ROLE_PURCHASE','ROLE_ADMIN')")
    public Integer delete(Integer id){
        return vendorService.delete(id);
    }
}