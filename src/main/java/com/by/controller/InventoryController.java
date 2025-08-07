package com.by.controller;

import com.by.entity.Inventory;
import com.by.entity.InventoryQuery;
import com.by.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(inventory)控制层
 * @author : heimi
 * @date : 2025-6-23
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController{
    @Autowired
    private InventoryService inventoryService;

     @GetMapping
     public List<Inventory> select(InventoryQuery query){
        return inventoryService.select(query);
    }
    
    @PostMapping 
    public Integer insert(@RequestBody Inventory inventory){
       return inventoryService.insert(inventory);
    }
    
    @PutMapping 
    public Integer update(@RequestBody Inventory product){
        return inventoryService.update(product);
    }
    
    @DeleteMapping
    public Integer delete(Integer id){
        return inventoryService.delete(id);
    }
}