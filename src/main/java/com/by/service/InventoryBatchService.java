package com.by.service;

import com.by.dao.InventoryBatchDao;
import com.by.entity.InventoryBatch;
import com.by.entity.InventoryBatchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ;(inventory_batch)表服务实现类
 * @author : heimi
 * @date : 2025-6-23
 */
@Service
public class InventoryBatchService{
     
    @Autowired
    private InventoryBatchDao inventoryBatchDao;
    
    public List<InventoryBatch> select(InventoryBatchQuery query) {
        return inventoryBatchDao.select(query);
    }
    
    public Integer insert(InventoryBatch inventoryBatch) {
        return inventoryBatchDao.insert(inventoryBatch);
    }
    
    public Integer update(InventoryBatch inventoryBatch) {
        return inventoryBatchDao.update(inventoryBatch);
    }
    
    public Integer delete(Integer id) {
        return inventoryBatchDao.delete(id);
    }
    
}