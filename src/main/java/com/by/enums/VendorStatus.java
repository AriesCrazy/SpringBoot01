package com.by.enums;

import java.util.Arrays;
import java.util.Optional;

public enum VendorStatus {
    NORMAL(1, "正常合作"),
    TERMINATE(2, "终止合作");

    private int code;
    private String message;

    VendorStatus(int code, String message) {
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

    public static VendorStatus getByCode(int code) {
        VendorStatus[] values = VendorStatus.values();
        Optional<VendorStatus> first = Arrays.stream(values).filter(item -> item.getCode() == code).findFirst();
        /*if (first.isPresent()) {
            return first.get();
        }
        return null;*/
        return first.orElse(null);
    }
}
