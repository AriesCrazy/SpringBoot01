package com.by.service;

import com.by.dao.IoItemDao;
import com.by.entity.IoItem;
import com.by.entity.IoItemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ;(io_item)表服务实现类
 * @author : heimi
 * @date : 2025-6-18
 */
@Service
public class IoItemService{
     
    @Autowired
    private IoItemDao ioItemDao;
    
    public List<IoItem> select(IoItemQuery query) {
        return ioItemDao.select(query);
    }
    
    public Integer insert(IoItem ioItem) {
        return ioItemDao.insert(ioItem);
    }
    
    public Integer update(IoItem ioItem) {
        return ioItemDao.update(ioItem);
    }
    
    public Integer delete(Integer id) {
        return ioItemDao.delete(id);
    }
    
}