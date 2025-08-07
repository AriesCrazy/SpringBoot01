package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.OoItem;
import com.by.entity.OoItemQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * ;(oo_item)表数据库访问层
 * @author : heimi
 * @date : 2025-6-24
 */
@Mapper
public interface OoItemDao{
    @PageX
    List<OoItem> select(OoItemQuery query);
    Integer insert(OoItem ooItem);
    Integer insertBatch(List<OoItem> ooItems);
    Integer update(OoItem ooItem);
    Integer delete(Integer id);
}