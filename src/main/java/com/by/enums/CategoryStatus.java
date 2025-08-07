package com.by.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;

public enum CategoryStatus {
    NORMAL(1, "售卖中"),
    OFF_SHELF(2, "已下架"),
    DELETE(120, "删除");

    @Getter @Setter
    private int code;
    @Getter @Setter
    private String message;

    CategoryStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CategoryStatus getByCode(int code) {
        CategoryStatus[] values = CategoryStatus.values();
        Optional<CategoryStatus> first = Arrays.stream(values).filter(item -> item.getCode() == code).findFirst();
        /*if (first.isPresent()) {
            return first.get();
        }
        return null;*/
        return first.orElse(null);
    }
}
