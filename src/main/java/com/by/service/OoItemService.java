package com.by.service;

import com.by.dao.OoItemDao;
import com.by.entity.OoItem;
import com.by.entity.OoItemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ;(oo_item)表服务实现类
 * @author : heimi
 * @date : 2025-6-24
 */
@Service
public class OoItemService{
     
    @Autowired
    private OoItemDao ooItemDao;
    
    public List<OoItem> select(OoItemQuery query) {
        return ooItemDao.select(query);
    }
    
    public Integer insert(OoItem ooItem) {
        return ooItemDao.insert(ooItem);
    }
    
    public Integer update(OoItem ooItem) {
        return ooItemDao.update(ooItem);
    }
    
    public Integer delete(Integer id) {
        return ooItemDao.delete(id);
    }
    
}