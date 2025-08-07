package com.by.service;

import cn.hutool.core.date.DateUtil;
import cn.smart.core.util.UserContext;
import com.by.dao.IoItemDao;
import com.by.dao.IoMasterDao;
import com.by.dao.PoMasterDao;
import com.by.dto.InventoryChangSource;
import com.by.entity.IoItem;
import com.by.entity.IoMaster;
import com.by.entity.IoMasterQuery;
import com.by.enums.InventoryChangType;
import com.by.enums.PoStatus;
import com.by.event.InventoryChangeEvent;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ;(io_master)表服务实现类
 *
 * @author : heimi
 * @date : 2025-6-18
 */
@Service
public class IoMasterService {

    @Autowired
    private IoMasterDao ioMasterDao;
    @Autowired
    private IoItemDao ioItemDao;
    @Autowired
    private PoMasterDao poMasterDao;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public List<IoMaster> select(IoMasterQuery query) {
        return ioMasterDao.select(query);
    }

    public Integer insert(IoMaster ioMaster) {
        //1.组装数据
        String ioId = "IO" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS");
        ioMaster.setId(ioId);
        List<IoItem> items = ioMaster.getItems();
        for (IoItem item : items) {
            item.setIoId(ioId);
            item.setLastUpdateBy(UserContext.get().getNickName());
            item.setExpiryDate(item.getProductionDate().plusDays(item.getShelfLife()));
        }
        //2.入库(注意事务失效!!!)
        IoMasterService aop = (IoMasterService) AopContext.currentProxy();
        aop.save(ioMaster);
        return 1;
    }

    @Transactional
    public void save(IoMaster ioMaster) {
        ioMasterDao.insert(ioMaster);
        ioItemDao.insertBatch(ioMaster.getItems());
        poMasterDao.updateStatus(ioMaster.getPoId(), PoStatus.FINISH.getCode());

        InventoryChangSource source = new InventoryChangSource();
        source.setType(InventoryChangType.IN);  //类型:入库
        source.setRefId(ioMaster.getId());  //refId:就是入库单id
        //发布库存变化事件
        InventoryChangeEvent inventoryChangeEvent = new InventoryChangeEvent(source);
        //使用观察者模式-解耦
        applicationEventPublisher.publishEvent(inventoryChangeEvent);
    }

    public Integer update(IoMaster ioMaster) {
        return ioMasterDao.update(ioMaster);
    }

    public Integer delete(Integer id) {
        return ioMasterDao.delete(id);
    }

}