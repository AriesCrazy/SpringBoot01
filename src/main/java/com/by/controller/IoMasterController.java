package com.by.controller;

import com.by.entity.IoMaster;
import com.by.entity.IoMasterQuery;
import com.by.service.IoMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(io_master)控制层
 * @author : heimi
 * @date : 2025-6-18
 */
@RestController
@RequestMapping("/api/io")
public class IoMasterController{
    @Autowired
    private IoMasterService ioMasterService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     public List<IoMaster> select(IoMasterQuery query){
        return ioMasterService.select(query);
    }
    
    @PostMapping 
    public Integer insert(@RequestBody IoMaster ioMaster){
       return ioMasterService.insert(ioMaster);
    }
    
    @PutMapping 
    public Integer update(@RequestBody IoMaster ioMaster){
        return ioMasterService.update(ioMaster);
    }
    
    @DeleteMapping
    public Integer delete(Integer id){
        return ioMasterService.delete(id);
    }
}