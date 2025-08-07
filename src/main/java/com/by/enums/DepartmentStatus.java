package com.by.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;

public enum DepartmentStatus {
    Enabled(1, "启用"),
    Disabled(2, "禁用");

    @Getter @Setter
    private int code;
    @Getter @Setter
    private String message;

    DepartmentStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static DepartmentStatus getByCode(int code) {
        DepartmentStatus[] values = DepartmentStatus.values();
        Optional<DepartmentStatus> first = Arrays.stream(values).filter(item -> item.getCode() == code).findFirst();
        /*if (first.isPresent()) {
            return first.get();
        }
        return null;*/
        return first.orElse(null);
    }
}
