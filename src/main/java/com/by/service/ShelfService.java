package com.by.service;

import cn.smart.core.util.UserContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.by.dao.ShelfDao;
import com.by.entity.Shelf;
import com.by.entity.ShelfQuery;
import com.by.enums.ShelfStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service //组件
public class ShelfService {

    @Autowired
    private ShelfDao shelfDao;

    public List<Shelf> select(ShelfQuery query) {
        LambdaQueryWrapper<Shelf> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(query.getId() != null, Shelf::getId, query.getId());
        queryWrapper.eq(query.getName() != null, Shelf::getName, query.getName());
        queryWrapper.eq(query.getWarehouseId() != null, Shelf::getWarehouseId, query.getWarehouseId());
        queryWrapper.eq(query.getStatus() != null, Shelf::getStatus, query.getStatus());

        List<Shelf> list = shelfDao.selectList(queryWrapper);
        return list;
    }

    public int insert(Shelf shelf) {
        return shelfDao.insert(shelf);
    }

    public int update(Shelf shelf) {
        LambdaUpdateWrapper<Shelf> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(shelf.getStatus()!=null, Shelf::getStatus, shelf.getStatus());
        updateWrapper.set(shelf.getName() != null, Shelf::getName, shelf.getName());
        updateWrapper.set(shelf.getBrief() != null, Shelf::getBrief, shelf.getBrief());
        updateWrapper.set(shelf.getSeq() != null, Shelf::getSeq, shelf.getSeq());
        updateWrapper.set( Shelf::getLastUpdateBy, UserContext.get().getNickName());
        updateWrapper.eq(Shelf::getId, shelf.getId());

        return shelfDao.update(updateWrapper);
    }

    public int delete(Integer id) {
        LambdaUpdateWrapper<Shelf> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Shelf::getStatus, ShelfStatus.OFF_SHELF.getCode());
        updateWrapper.eq(Shelf::getId, id);
        return shelfDao.update(updateWrapper);
    }

    public List<Map> selectTree() {
        return shelfDao.selectTree();
    }
}
