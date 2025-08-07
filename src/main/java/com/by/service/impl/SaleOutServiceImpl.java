package com.by.service.impl;

import com.by.dao.InventoryDao;
import com.by.dao.OoItemDao;
import com.by.dto.InventoryChangSource;
import com.by.entity.InventoryQuery;
import com.by.entity.OoItem;
import com.by.entity.OoItemQuery;
import com.by.service.IInventoryOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SALE_OUT")
public class SaleOutServiceImpl implements IInventoryOptionService {
    @Autowired
    private OoItemDao ooItemDao;
    @Autowired
    private InventoryDao inventoryDao;


    @Override
    public void option(InventoryChangSource source) {
        String ooId = source.getRefId();
        System.out.println(ooId);
        // 构造查询条件，查询与入库单ID匹配的入库项
        OoItemQuery query = new OoItemQuery().setOoId(ooId);
        List<OoItem> ooItems = ooItemDao.select(query);
        // 遍历每个入库项，更新库存和批次信息
        for (OoItem ooItem : ooItems) {
            // 构造库存查询条件，查询与产品ID匹配的库存记录
            InventoryQuery inventoryQuery = InventoryQuery.builder().id(ooItem.getProductId()).build();
            // 找到匹配的库存记录，更新库存数量
            inventoryDao.updateSaleCount(ooItem.getProductId(), ooItem.getQty());


            /*// 构造批次ID，用于查询或插入批次信息
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
            }*/
        }
    }
}
