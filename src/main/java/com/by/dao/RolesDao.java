package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.Roles;
import com.by.entity.RolesQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * ;(roles)表数据库访问层
 * @author : heimi
 * @date : 2025-6-21
 */
@Mapper
public interface RolesDao{
    @PageX
    List<Roles> select(RolesQuery query);
    Integer insert(Roles roles);
    Integer insertBatch(List<Roles> rolesses);
    Integer update(Roles roles);
    Integer delete(Integer id);
}