package com.by.service;

import com.by.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public void transfer(int fromId, int toId, double money) {
        accountDao.minusMoney(fromId, money);
        int i = 5 / 0;
        accountDao.addMoney(toId, money);
    }
}
