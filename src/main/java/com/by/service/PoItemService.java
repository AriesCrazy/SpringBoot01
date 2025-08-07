package com.by.service;

import com.by.dao.PoItemDao;
import com.by.entity.PoItem;
import com.by.entity.PoItemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ;(po_item)表服务实现类
 * @author : heimi
 * @date : 2025-6-12
 */
@Service
public class PoItemService{
     
    @Autowired
    private PoItemDao poItemDao;
    
    public List<PoItem> select(PoItemQuery query) {
        return poItemDao.select(query);
    }
    
    public Integer insert(PoItem poItem) {
        return poItemDao.insert(poItem);
    }
    
    public Integer update(PoItem poItem) {
        return poItemDao.update(poItem);
    }
    
    public Integer delete(Integer id) {
        return poItemDao.delete(id);
    }
    
}