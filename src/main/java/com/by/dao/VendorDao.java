package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.Vendor;
import com.by.entity.VendorQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * ;(vendor)表数据库访问层
 * @author : heimi
 * @date : 2025-6-6
 */
@Mapper
public interface VendorDao{
    @PageX
    List<Vendor> select(VendorQuery query);
    Integer insert(Vendor vendor);
    Integer insertBatch(List<Vendor> vendors);
    Integer update(Vendor vendor);
    Integer delete(Integer id);
}