package com.by.enums;

import java.util.Arrays;
import java.util.Optional;

public enum PriceStatus {
    NORMAL(1, "使用中"),
    OFF_SHELF(0, "未使用"),
    DELETE(120, "删除");

    private int code;
    private String message;

    PriceStatus(int code, String message) {
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

    public static PriceStatus getByCode(int code) {
        PriceStatus[] values = PriceStatus.values();
        Optional<PriceStatus> first = Arrays.stream(values).filter(item -> item.getCode() == code).findFirst();
        /*if (first.isPresent()) {
            return first.get();
        }
        return null;*/
        return first.orElse(null);
    }
}
