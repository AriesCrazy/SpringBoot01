package com.by.controller;

import com.by.entity.OoItem;
import com.by.entity.OoItemQuery;
import com.by.service.OoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(oo_item)控制层
 * @author : heimi
 * @date : 2025-6-24
 */
@RestController
@RequestMapping("/api/ooItem")
public class OoItemController{
    @Autowired
    private OoItemService ooItemService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     public List<OoItem> select(OoItemQuery query){
        return ooItemService.select(query);
    }
    
    @PostMapping 
    public Integer insert(@RequestBody OoItem product){
       return ooItemService.insert(product);
    }
    
    @PutMapping 
    public Integer update(@RequestBody OoItem product){
        return ooItemService.update(product);
    }
    
    @DeleteMapping
    public Integer delete(Integer id){
        return ooItemService.delete(id);
    }
}