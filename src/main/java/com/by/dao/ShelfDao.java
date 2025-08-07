package com.by.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.by.entity.Shelf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShelfDao extends BaseMapper<Shelf> {

    @Select("select id*(-1) as id,name,0 as parentId from warehouse\n" +
            "\n" +
            "union\n" +
            "\n" +
            "select id,name,warehouseId*(-1) as parentId from shelf")
    List<Map> selectTree();
}
