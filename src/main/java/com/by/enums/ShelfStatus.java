package com.by.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ShelfStatus {
    NORMAL(1, "正常"),
    OFF_SHELF(2, "弃用");

    private int code;
    private String message;

    ShelfStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ShelfStatus getByCode(int code) {
        ShelfStatus[] values = ShelfStatus.values();
        Optional<ShelfStatus> first = Arrays.stream(values).filter(item -> item.getCode() == code).findFirst();
        /*if (first.isPresent()) {
            return first.get();
        }
        return null;*/
        return first.orElse(null);
    }
}
