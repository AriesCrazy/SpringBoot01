package com.by.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String tel;
    private String email;
    private String password;
    private String nickName;
    private List<Integer> roleids;
}
