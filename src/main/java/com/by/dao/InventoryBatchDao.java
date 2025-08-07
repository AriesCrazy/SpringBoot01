package com.by.dao;

import cn.smart.core.annotation.PageX;
import com.by.entity.InventoryBatch;
import com.by.entity.InventoryBatchQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

 /**
 * ;(inventory_batch)表数据库访问层
 * @author : heimi
 * @date : 2025-6-23
 */
@Mapper
public interface InventoryBatchDao{
    @PageX
    List<InventoryBatch> select(InventoryBatchQuery query);
    Integer insert(InventoryBatch inventoryBatch);
    Integer insertBatch(List<InventoryBatch> inventoryBatchs);
    Integer update(InventoryBatch inventoryBatch);
    Integer delete(Integer id);

    @Update("update inventory_batch set qty = qty + #{optionQty} where batchId = #{batchId}")
    Integer updateBatchQty(String batchId,Integer optionQty);
}