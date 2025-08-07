package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.OoMaster;
import com.by.entity.OoMasterQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * ;(oo_master)表数据库访问层
 * @author : heimi
 * @date : 2025-6-24
 */
@Mapper
public interface OoMasterDao{
    @PageX
    List<OoMaster> select(OoMasterQuery query);
    Integer insert(OoMaster ooMaster);
    Integer insertBatch(List<OoMaster> ooMasters);
    Integer update(OoMaster ooMaster);
    Integer delete(Integer id);
}