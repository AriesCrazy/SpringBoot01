package com.by.controller;

import com.by.entity.Permissions;
import com.by.entity.PermissionsQuery;
import com.by.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(permissions)控制层
 *
 * @author : heimi
 * @date : 2025-6-23
 */
@RestController
@RequestMapping("/api/permissions")
public class PermissionsController {
    @Autowired
    private PermissionsService permissionsService;

    /**
     * 查询接口
     */
    @GetMapping
    public List<Permissions> select(PermissionsQuery query) {
        return permissionsService.select(query);
    }

    @PostMapping
    public Integer insert(@RequestBody Permissions product) {
        return permissionsService.insert(product);
    }

    @GetMapping("/add")
    public Integer addPermission(@RequestParam("roleId") Integer roleId, @RequestParam("permissionId") Integer permissionId) {
        return permissionsService.addPermission(roleId, permissionId);
    }

    @GetMapping("/delete")
    public Integer deletePermission(@RequestParam("roleId") Integer roleId, @RequestParam("permissionId") Integer permissionId) {
        return permissionsService.deletePermission(roleId, permissionId);
    }

    @PutMapping
    public Integer update(@RequestBody Permissions product) {
        return permissionsService.update(product);
    }

    @DeleteMapping
    public Integer delete(Integer id) {
        return permissionsService.delete(id);
    }
}