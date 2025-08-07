package com.by.controller;

import com.by.entity.OoMaster;
import com.by.entity.OoMasterQuery;
import com.by.service.OoMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(oo_master)控制层
 * @author : heimi
 * @date : 2025-6-24
 */
@RestController
@RequestMapping("/api/oo")
public class OoMasterController{
    @Autowired
    private OoMasterService ooMasterService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     public List<OoMaster> select(OoMasterQuery query){
        return ooMasterService.select(query);
    }
    
    @PostMapping 
    public Integer insert(@RequestBody OoMaster ooMaster){
       return ooMasterService.insert(ooMaster);
    }
    
    @PutMapping 
    public Integer update(@RequestBody OoMaster product){
        return ooMasterService.update(product);
    }
    
    @DeleteMapping
    public Integer delete(Integer id){
        return ooMasterService.delete(id);
    }
}