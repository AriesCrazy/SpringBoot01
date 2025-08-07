package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.PoMaster;
import com.by.entity.PoMasterQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ;(po_master)表数据库访问层
 *
 * @author : heimi
 * @date : 2025-6-12
 */
@Mapper
public interface PoMasterDao {
    @PageX
    List<PoMaster> select(PoMasterQuery query);

    Integer insert(PoMaster poMaster);

    Integer insertBatch(List<PoMaster> poMasters);

    Integer update(PoMaster poMaster);

    Integer delete(Integer id);

    Integer updateStatus(String id, Integer status);
}