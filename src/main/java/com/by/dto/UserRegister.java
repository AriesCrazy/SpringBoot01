package com.by.dto;

import lombok.Data;

@Data
public class UserRegister {
    private String tel;
    private String email;
    private String password;
    private String nickName;
    private Integer code;

}