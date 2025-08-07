package com.by.controller;

import com.by.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ;(category)控制层
 * @author : heimi
 * @date : 2025-4-14
 */
@RestController
@RequestMapping("/api/ai")
public class AiController {
    @Autowired
    private AiService aiService;
    
    /** 
     * 查询接口
     */
     @GetMapping
     public String select(@RequestParam("request") String request){
        return aiService.select(request);
    }
}