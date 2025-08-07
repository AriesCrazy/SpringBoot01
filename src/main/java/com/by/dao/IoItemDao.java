package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.IoItem;
import com.by.entity.IoItemQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * ;(io_item)表数据库访问层
 * @author : heimi
 * @date : 2025-6-18
 */
@Mapper
public interface IoItemDao{
    @PageX
    List<IoItem> select(IoItemQuery query);
    Integer insert(IoItem ioItem);
    Integer insertBatch(List<IoItem> ioItems);
    Integer update(IoItem ioItem);
    Integer delete(Integer id);
}