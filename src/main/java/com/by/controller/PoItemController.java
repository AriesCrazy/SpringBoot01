package com.by.controller;

import com.by.entity.PoItem;
import com.by.entity.PoItemQuery;
import com.by.service.PoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(po_item)控制层
 * @author : heimi
 * @date : 2025-6-12
 */
@RestController
@RequestMapping("/api/poitem")
public class PoItemController{
    @Autowired
    private PoItemService poItemService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     public List<PoItem> select(PoItemQuery query){
        return poItemService.select(query);
    }
    
    @PostMapping 
    public Integer insert(@RequestBody PoItem product){
       return poItemService.insert(product);
    }
    
    @PutMapping 
    public Integer update(@RequestBody PoItem product){
        return poItemService.update(product);
    }
    
    @DeleteMapping
    public Integer delete(Integer id){
        return poItemService.delete(id);
    }
}