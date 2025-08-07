package com.by.entity;

import lombok.Data;

@Data
public class UserQuery {
    private Integer id;
    private String tel;
    private String email;
    private String nickName;
}