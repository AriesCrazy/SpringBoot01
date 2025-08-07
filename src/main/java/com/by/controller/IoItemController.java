package com.by.controller;

import com.by.entity.IoItem;
import com.by.entity.IoItemQuery;
import com.by.service.IoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(io_item)控制层
 * @author : heimi
 * @date : 2025-6-18
 */
@RestController
@RequestMapping("/api/ioitem")
public class IoItemController{
    @Autowired
    private IoItemService ioItemService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     public List<IoItem> select(IoItemQuery query){
        return ioItemService.select(query);
    }
    
    @PostMapping 
    public Integer insert(@RequestBody IoItem ioItem){
       return ioItemService.insert(ioItem);
    }
    
    @PutMapping 
    public Integer update(@RequestBody IoItem ioItem){
        return ioItemService.update(ioItem);
    }
    
    @DeleteMapping
    public Integer delete(Integer id){
        return ioItemService.delete(id);
    }
}