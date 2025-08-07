package com.by.dao;

import com.by.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDao {
    List<Account> select();
    int addMoney(int id, double money1);
    int minusMoney(int id, double money1);
}
