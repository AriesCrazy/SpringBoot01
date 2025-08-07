package com.by.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.by.dao.InventoryBatchDao;
import com.by.dao.InventoryDao;
import com.by.dao.IoItemDao;
import com.by.dto.InventoryChangSource;
import com.by.entity.*;
import com.by.service.IInventoryOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 入库操作实现类
 */
@Service("IN")
public class InServiceImpl implements IInventoryOptionService {
    @Autowired
    private IoItemDao ioItemDao;
    @Autowired
    private InventoryDao inventoryDao;
    @Autowired
    private InventoryBatchDao inventoryBatchDao;

    /**
     * 执行入库操作
     *
     * @param source 入库变更源，包含入库单ID等信息
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void option(InventoryChangSource source) {
        // 获取入库单ID
        String ioId = source.getRefId();
        // 构造查询条件，查询与入库单ID匹配的入库项
        IoItemQuery query = new IoItemQuery().setIoId(ioId);
        List<IoItem> ioItems = ioItemDao.select(query);

        // 遍历每个入库项，更新库存和批次信息
        for (IoItem ioItem : ioItems) {
            // 构造库存查询条件，查询与产品ID匹配的库存记录
            InventoryQuery inventoryQuery = InventoryQuery.builder().id(ioItem.getProductId()).build();
            List<Inventory> inventoryList = inventoryDao.select(inventoryQuery);
            // 如果找到匹配的库存记录，更新库存数量
            if (ObjectUtil.isNotEmpty(inventoryList)) {
                inventoryDao.updateQty(ioItem.getProductId(), ioItem.getQty());
            } else {
                // 如果没有找到匹配的库存记录，插入新的库存记录
                Inventory inventory1 = Inventory.builder()
                        .id(ioItem.getProductId())
                        .productName(ioItem.getProductName())
                        .qty(ioItem.getQty()).saleCount(0)
                        .version(0).build();
                inventoryDao.insert(inventory1);
            }

            // 构造批次ID，用于查询或插入批次信息
            String batchId = "B" + ioItem.getProductId() + "-"
                    + DateUtil.format(ioItem.getProductionDate().atStartOfDay(), "yyyyMMdd");
            InventoryBatchQuery batchQuery = InventoryBatchQuery.builder().batchId(batchId).build();
            List<InventoryBatch> batchList = inventoryBatchDao.select(batchQuery);

            // 如果找到匹配的批次记录，更新批次数量
            if (ObjectUtil.isNotEmpty(batchList)){
                inventoryBatchDao.updateBatchQty(batchId, ioItem.getQty());
            } else {
                // 如果没有找到匹配的批次记录，插入新的批次记录
                InventoryBatch inventoryBatch = InventoryBatch.builder()
                        .batchId(batchId)
                        .productId(ioItem.getProductId())
                        .productName(ioItem.getProductName())
                        .productionDate(ioItem.getProductionDate())
                        .brief(ioItem.getBrief())
                        .expiryDate(ioItem.getExpiryDate())
                        .qty(ioItem.getQty())
                        .shelfId(ioItem.getShelfId())
                        .shelfLife(ioItem.getShelfLife())
                        .build();
                inventoryBatchDao.insert(inventoryBatch);
            }
        }
    }
}
