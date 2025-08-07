package com.by.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

//@WebListener
@Slf4j
public class ServletRequestListener1 implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.debug("有人请求了");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.debug("请求结束了");
    }
}
