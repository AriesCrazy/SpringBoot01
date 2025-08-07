package com.by.service;

import com.by.dao.VendorDao;
import com.by.entity.Vendor;
import com.by.entity.VendorQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ;(vendor)表服务实现类
 * @author : heimi
 * @date : 2025-6-6
 */
@Service
public class VendorService{
     
    @Autowired
    private VendorDao vendorDao;
    
    public List<Vendor> select(VendorQuery query) {
        return vendorDao.select(query);
    }
    
    public Integer insert(Vendor vendor) {
        return vendorDao.insert(vendor);
    }
    
    public Integer update(Vendor vendor) {
        return vendorDao.update(vendor);
    }
    
    public Integer delete(Integer id) {
        return vendorDao.delete(id);
    }
    
}