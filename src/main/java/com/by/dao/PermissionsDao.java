package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.Permissions;
import com.by.entity.PermissionsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

 /**
 * ;(permissions)表数据库访问层
 * @author : heimi
 * @date : 2025-6-23
 */
@Mapper
public interface PermissionsDao{
    @PageX
    List<Permissions> select(PermissionsQuery query);
    Integer insert(Permissions permissions);
    Integer addPermission(@Param("role_id")Integer roldId,@Param("permission_id")Integer permissionId);
    Integer deletePermission(@Param("role_id")Integer roldId,@Param("permission_id")Integer permissionId);
    Integer insertBatch(List<Permissions> permissionss);
    Integer update(Permissions permissions);
    Integer delete(Integer id);
}