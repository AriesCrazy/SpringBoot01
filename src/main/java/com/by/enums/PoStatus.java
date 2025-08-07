package com.by.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;

public enum PoStatus {
    NEW(1, "新建(待审核)"),
    APPROVE(2, "审核通过"),
    DISTRIBUTION(3, "配货中"),
    FINISH(4, "已入库"),
    REJECT(10, "驳回"),
    CANCEL(20, "作废");

    @Getter @Setter
    private int code;
    @Getter @Setter
    private String message;

    PoStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PoStatus getByCode(int code) {
        PoStatus[] values = PoStatus.values();
        Optional<PoStatus> first = Arrays.stream(values).filter(item -> item.getCode() == code).findFirst();
        /*if (first.isPresent()) {
            return first.get();
        }
        return null;*/
        return first.orElse(null);
    }
}
