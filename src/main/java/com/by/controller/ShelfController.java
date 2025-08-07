package com.by.controller;

import com.by.entity.Shelf;
import com.by.entity.ShelfQuery;
import com.by.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shelf")
public class ShelfController {
    @Autowired //自动注入
    private ShelfService shelfService;

    @GetMapping//查询 万能接口 ?id=1  ?ids=1,2,3 ?name=小米  ?categoryId=2
    public List<Shelf> select(ShelfQuery query) {
        return shelfService.select(query);
    }

    @GetMapping("/tree")//查询 万能接口 ?id=1  ?ids=1,2,3 ?name=小米  ?categoryId=2
    public List<Map> selectTree() {
        return shelfService.selectTree();
    }


    @PostMapping  // 从http协议的请求体
    public int insert(@RequestBody Shelf shelf) {
        return shelfService.insert(shelf);
    }

    @PutMapping
    public int update(@RequestBody Shelf shelf) {
        return shelfService.update(shelf);
    }

    @DeleteMapping
    public int delete(@RequestParam Integer id) {
        return shelfService.delete(id);
    }
}
