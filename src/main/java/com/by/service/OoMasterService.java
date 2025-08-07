package com.by.service;

import cn.hutool.core.date.DateUtil;
import cn.smart.core.util.UserContext;
import com.by.dao.OoItemDao;
import com.by.dao.OoMasterDao;
import com.by.dto.InventoryChangSource;
import com.by.entity.OoItem;
import com.by.entity.OoMaster;
import com.by.entity.OoMasterQuery;
import com.by.enums.InventoryChangType;
import com.by.event.InventoryChangeEvent;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ;(oo_master)表服务实现类
 * @author : heimi
 * @date : 2025-6-24
 */
@Service
public class OoMasterService{
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
     
    @Autowired
    private OoMasterDao ooMasterDao;
    @Autowired
    private OoItemDao ooItemDao;
    
    public List<OoMaster> select(OoMasterQuery query) {
        return ooMasterDao.select(query);
    }
    
    public Integer insert(OoMaster ooMaster) {
        //1.组装数据
        String ooId = "OO" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS");
        ooMaster.setId(ooId);
        List<OoItem> items = ooMaster.getItems();
        for (OoItem item : items) {
            item.setOoId(ooId);
            item.setLastUpdateBy(UserContext.get().getNickName());
        }
        //2.出库(注意事务失效!!!)
        OoMasterService aop = (OoMasterService) AopContext.currentProxy();
        aop.save(ooMaster);
        return 1;
    }

    @Transactional
    public void save(OoMaster ooMaster) {
        ooMasterDao.insert(ooMaster);
        ooItemDao.insertBatch(ooMaster.getItems());

        InventoryChangSource source = new InventoryChangSource();
        source.setType(InventoryChangType.SALE_OUT);
        source.setRefId(ooMaster.getId());  //refId:就是出库单id
        //发布库存变化事件
        InventoryChangeEvent inventoryChangeEvent = new InventoryChangeEvent(source);
        //使用观察者模式-解耦
        applicationEventPublisher.publishEvent(inventoryChangeEvent);
    }
    
    public Integer update(OoMaster ooMaster) {
        return ooMasterDao.update(ooMaster);
    }
    
    public Integer delete(Integer id) {
        return ooMasterDao.delete(id);
    }
    
}