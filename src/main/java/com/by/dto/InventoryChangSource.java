package com.by.dto;

import com.by.enums.InventoryChangType;
import lombok.Data;

@Data
public class InventoryChangSource {
    private InventoryChangType type;
    private String refId;//第三方单据id

}
