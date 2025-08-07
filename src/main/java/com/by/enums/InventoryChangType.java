package com.by.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;

public enum InventoryChangType {
    IN(1, "入库"),
    SALE_OUT(2, "销售出库"),
    BROKEN_OUT(3, "破损出库"),
    EXPIRE_OUT(4, "过期出库"),
    ALLOT_OUT(5, "调拨出库");


    @Getter @Setter
    private int code;
    @Getter @Setter
    private String message;

    InventoryChangType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static InventoryChangType getByCode(int code) {
        InventoryChangType[] values = InventoryChangType.values();
        Optional<InventoryChangType> first = Arrays.stream(values).filter(item -> item.getCode() == code).findFirst();
        /*if (first.isPresent()) {
            return first.get();
        }
        return null;*/
        return first.orElse(null);
    }
}
