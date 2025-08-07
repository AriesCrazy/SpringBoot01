package com.by.entity;

import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;

 /**
 * ;
 * @author : heimi
 * @date : 2025-6-23
 */
@Data
public class Permissions extends BaseEntity {
    /**  */
    private Integer id ;
    /**  */
    private String name ;
    /**  */
    private String description ;
    /**  */
    private Integer resource_id ;

}