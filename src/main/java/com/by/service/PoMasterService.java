package com.by.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.smart.core.exception.BizException;
import cn.smart.core.util.UserContext;
import com.by.dao.PoItemDao;
import com.by.dao.PoMasterDao;
import com.by.entity.PoItem;
import com.by.entity.PoMaster;
import com.by.entity.PoMasterQuery;
import com.by.enums.PoStatus;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * ;(po_master)表服务实现类
 *
 * @author : heimi
 * @date : 2025-6-12
 */
@Service
public class PoMasterService {

    @Autowired
    private PoMasterDao poMasterDao;
    @Autowired
    private PoItemDao poItemDao;

    public List<PoMaster> select(PoMasterQuery query) {
        return poMasterDao.select(query);
    }

    public Integer insert(PoMaster poMaster) {
        String poId = "PO" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS");
        poMaster.setId(poId);
        poMaster.setStatus(PoStatus.NEW.getCode());
        List<PoItem> items = poMaster.getItems();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (PoItem item : items) {
            item.setPoId(poId);
            item.setLastUpdateBy(UserContext.get().getNickName());
            totalPrice = NumberUtil.add(totalPrice, item.getPrice().multiply(new BigDecimal(item.getQty())));
        }
        if (totalPrice.doubleValue() != poMaster.getTotalPrice().doubleValue()) {
            throw new BizException(407, "总价格不正确");
        }

        // to do
        if (totalPrice.doubleValue() <= 2000) {
            poMaster.setStatus(PoStatus.DISTRIBUTION.getCode());
        } else {
            //发送钉钉通知等 请求审核
        }

        /*poMasterDao.insert(poMaster);
        poItemDao.insertBatch(poMaster.getItems());*/
        PoMasterService aop = (PoMasterService) AopContext.currentProxy();
        aop.save(poMaster);
        return 0;
    }

    @Transactional
    public void save(PoMaster poMaster) {
        poMasterDao.insert(poMaster);
        poItemDao.insertBatch(poMaster.getItems());
    }

    public Integer update(PoMaster poMaster) {
        return poMasterDao.update(poMaster);
    }

    public Integer approve(String id) {
        return poMasterDao.updateStatus(id, PoStatus.APPROVE.getCode());
    }

    public Integer reject(String id) {
        return poMasterDao.updateStatus(id, PoStatus.REJECT.getCode());
    }

    public Integer interrupt(String id) {
        return poMasterDao.updateStatus(id, PoStatus.CANCEL.getCode());
    }

    public Integer startDelivery(String id) {
        return poMasterDao.updateStatus(id, PoStatus.DISTRIBUTION.getCode());
    }

    public Integer delete(Integer id) {
        return poMasterDao.delete(id);
    }

}