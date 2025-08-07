package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.Inventory;
import com.by.entity.InventoryQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

 /**
 * ;(inventory)表数据库访问层
 * @author : heimi
 * @date : 2025-6-23
 */
@Mapper
public interface InventoryDao{
    @PageX
    List<Inventory> select(InventoryQuery query);
    Integer insert(Inventory inventory);
    Integer insertBatch(List<Inventory> inventorys);
    Integer update(Inventory inventory);
    Integer delete(Integer id);

    @Update("update inventory set qty = qty + #{optionQty} ,version=version+1 where id = #{productId}")
    Integer updateQty(Integer productId, Integer optionQty);
     @Update("update inventory set saleCount = saleCount + #{optionQty} ,version=version+1 where id = #{productId}")
     Integer updateSaleCount(Integer productId, Integer optionQty);
}