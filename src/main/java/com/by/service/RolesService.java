package com.by.service;

import com.by.dao.RolesDao;
import com.by.entity.Roles;
import com.by.entity.RolesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ;(roles)表服务实现类
 * @author : heimi
 * @date : 2025-6-21
 */
@Service
public class RolesService{
     
    @Autowired
    private RolesDao rolesDao;
    
    public List<Roles> select(RolesQuery query) {
        return rolesDao.select(query);
    }
    
    public Integer insert(Roles roles) {
        return rolesDao.insert(roles);
    }
    
    public Integer update(Roles roles) {
        return rolesDao.update(roles);
    }
    
    public Integer delete(Integer id) {
        return rolesDao.delete(id);
    }
    
}