package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.PoItem;
import com.by.entity.PoItemQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * ;(po_item)表数据库访问层
 * @author : heimi
 * @date : 2025-6-12
 */
@Mapper
public interface PoItemDao{
    @PageX
    List<PoItem> select(PoItemQuery query);
    Integer insert(PoItem poItem);
    Integer insertBatch(List<PoItem> poItems);
    Integer update(PoItem poItem);
    Integer delete(Integer id);
}