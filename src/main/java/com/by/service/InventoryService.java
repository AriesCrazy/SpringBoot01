package com.by.service;

import com.by.dao.InventoryDao;
import com.by.dto.InventoryChangSource;
import com.by.entity.Inventory;
import com.by.entity.InventoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ;(inventory)表服务实现类
 *
 * @author : heimi
 * @date : 2025-6-23
 */
@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;
    @Autowired
    private Map<String, IInventoryOptionService> optionServiceMap;
    
    public List<Inventory> select(InventoryQuery query) {
        return inventoryDao.select(query);
    }

    public void option(InventoryChangSource source) {
        String type = source.getType().toString();
        IInventoryOptionService iInventoryOptionService = optionServiceMap.get(type);
        iInventoryOptionService.option(source);
    }

    public Integer insert(Inventory inventory) {
        return inventoryDao.insert(inventory);
    }

    public Integer update(Inventory inventory) {
        return inventoryDao.update(inventory);
    }

    public Integer delete(Integer id) {
        return inventoryDao.delete(id);
    }

}