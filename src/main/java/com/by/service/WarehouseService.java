package com.by.service;

import cn.smart.core.util.UserContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.by.dao.WarehouseDao;
import com.by.entity.Warehouse;
import com.by.entity.WarehouseQuery;
import com.by.enums.WarehouseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //组件
public class WarehouseService {

    @Autowired
    private WarehouseDao warehouseDao;

    public List<Warehouse> select(WarehouseQuery query) {
        LambdaQueryWrapper<Warehouse> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(query.getId() != null, Warehouse::getId, query.getId());
        queryWrapper.eq(query.getStatus() != null, Warehouse::getStatus, query.getStatus());

        List<Warehouse> list = warehouseDao.selectList(queryWrapper);
        return list;
    }

    public int insert(Warehouse warehouse) {
        return warehouseDao.insert(warehouse);
    }

    public int update(Warehouse warehouse) {
        LambdaUpdateWrapper<Warehouse> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(warehouse.getStatus()!=null, Warehouse::getStatus, warehouse.getStatus());
        updateWrapper.set(warehouse.getName() != null, Warehouse::getName, warehouse.getName());
        updateWrapper.set(warehouse.getBrief() != null, Warehouse::getBrief, warehouse.getBrief());
        updateWrapper.set(warehouse.getSeq() != null, Warehouse::getSeq, warehouse.getSeq());
        updateWrapper.set(warehouse.getImg() != null,
                Warehouse::getImg,
                warehouse.getImg(),
                "typeHandler = com.by.typehandler.JsonTypeHandler"
        );
        updateWrapper.set( Warehouse::getLastUpdateBy, UserContext.get().getNickName());
        updateWrapper.eq(Warehouse::getId, warehouse.getId());

        return warehouseDao.update(updateWrapper);
    }

    public int delete(Integer id) {
        LambdaUpdateWrapper<Warehouse> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Warehouse::getStatus, WarehouseStatus.OFF_SHELF.getCode());
        updateWrapper.eq(Warehouse::getId, id);
        return warehouseDao.update(updateWrapper);
    }
}
