package com.by.event;

import org.springframework.context.ApplicationEvent;

//库存变化事件
public class InventoryChangeEvent extends ApplicationEvent {
    public InventoryChangeEvent(Object source) {
        super(source);
    }
}
