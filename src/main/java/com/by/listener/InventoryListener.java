package com.by.listener;

import com.by.dto.InventoryChangSource;
import com.by.event.InventoryChangeEvent;
import com.by.service.InventoryService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

public class InventoryListener implements ApplicationListener<InventoryChangeEvent>, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(InventoryChangeEvent event) {
        InventoryChangSource source = (InventoryChangSource) event.getSource();
        /*if (source.getType().getCode()== InventoryChangType.IN.getCode()) {
            //获取到事件源    处理入库
        }else if (source.getType().getCode()== InventoryChangType.SALE_OUT.getCode()) {
            //获取到事件源    处理销售出库
        }else if (source.getType().getCode()== InventoryChangType.EXPIRE_OUT.getCode()) {
            //获取到事件源    处理过期出库
        }...*/
        InventoryService bean = applicationContext.getBean(InventoryService.class);
        bean.option(source);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
