package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.IoMaster;
import com.by.entity.IoMasterQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * ;(io_master)表数据库访问层
 * @author : heimi
 * @date : 2025-6-18
 */
@Mapper
public interface IoMasterDao{
    @PageX
    List<IoMaster> select(IoMasterQuery query);
    Integer insert(IoMaster ioMaster);
    Integer insertBatch(List<IoMaster> ioMasters);
    Integer update(IoMaster ioMaster);
    Integer delete(Integer id);
}