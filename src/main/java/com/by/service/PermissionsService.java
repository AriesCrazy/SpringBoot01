package com.by.service;

import com.by.dao.PermissionsDao;
import com.by.entity.Permissions;
import com.by.entity.PermissionsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ;(permissions)表服务实现类
 *
 * @author : heimi
 * @date : 2025-6-23
 */
@Service
public class PermissionsService {

    @Autowired
    private PermissionsDao permissionsDao;

    public List<Permissions> select(PermissionsQuery query) {
        return permissionsDao.select(query);
    }

    public Integer insert(Permissions permissions) {
        return permissionsDao.insert(permissions);
    }

    public Integer addPermission(Integer roldId, Integer permissionId) {
        return permissionsDao.addPermission(roldId, permissionId);
    }

    public Integer deletePermission(Integer roldId, Integer permissionId) {
        return permissionsDao.deletePermission(roldId, permissionId);
    }

    public Integer update(Permissions permissions) {
        return permissionsDao.update(permissions);
    }

    public Integer delete(Integer id) {
        return permissionsDao.delete(id);
    }

}