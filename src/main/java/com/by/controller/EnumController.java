package com.by.controller;

import cn.hutool.json.JSONArray;
import com.by.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/enum")
public class EnumController {
    @Autowired
    private EnumService enumService;

    @GetMapping//?enumClassName=ProductStatus
    public JSONArray select(String enumClassName) {
        return enumService.select(enumClassName);
    }
}
