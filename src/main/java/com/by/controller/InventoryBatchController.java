package com.by.controller;

import com.by.entity.InventoryBatch;
import com.by.entity.InventoryBatchQuery;
import com.by.service.InventoryBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(inventory_batch)控制层
 * @author : heimi
 * @date : 2025-6-23
 */
@RestController
@RequestMapping("/api/inventoryBatch")
public class InventoryBatchController{
    @Autowired
    private InventoryBatchService inventoryBatchService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     public List<InventoryBatch> select(InventoryBatchQuery query){
        return inventoryBatchService.select(query);
    }
    
    @PostMapping 
    public Integer insert(@RequestBody InventoryBatch inventoryBatch){
       return inventoryBatchService.insert(inventoryBatch);
    }
    
    @PutMapping 
    public Integer update(@RequestBody InventoryBatch product){
        return inventoryBatchService.update(product);
    }
    
    @DeleteMapping
    public Integer delete(Integer id){
        return inventoryBatchService.delete(id);
    }
}