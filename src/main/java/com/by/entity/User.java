package com.by.entity;

import lombok.Data;

import java.util.List;

@Data
public class User extends BaseEntity{
    private Integer id;
    private String tel;
    private String email;
    private String password;
    private String nickName;

    private List<Roles> roles;
    private List<Permissions> permissions; // 角色对应的权限列表

}