package com.by.controller;

import com.by.entity.Warehouse;
import com.by.entity.WarehouseQuery;
import com.by.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired //自动注入
    private WarehouseService warehouseService;

    @GetMapping//查询 万能接口 ?id=1  ?ids=1,2,3 ?name=小米  ?categoryId=2
    public List<Warehouse> select(WarehouseQuery query) {
        return warehouseService.select(query);
    }


    @PostMapping  // 从http协议的请求体
    public int insert(@RequestBody Warehouse  warehouse) {
        return warehouseService.insert(warehouse);
    }

    @PutMapping
    public int update(@RequestBody Warehouse warehouse) {
        return warehouseService.update(warehouse);
    }

    @DeleteMapping
    public int delete(@RequestParam Integer id) {
        return warehouseService.delete(id);
    }
}
