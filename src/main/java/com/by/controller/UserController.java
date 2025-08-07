package com.by.controller;

import com.by.dto.LoginDTO;
import com.by.dto.UserDTO;
import com.by.dto.UserRegister;
import com.by.entity.User;
import com.by.entity.UserQuery;
import com.by.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/send/code")
    public int sendCode(String email) {
        return userService.sendCode(email);
    }

    /*@GetMapping("/test")
    public String test() {
        return "test";
    }*/

    @PostMapping("/register")
    public int sendCode(@RequestBody UserRegister userRegister) {
        return userService.register(userRegister);
    }

    @PostMapping("/login")
    public Map login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @GetMapping
    public List<User> select(UserQuery query){
        return userService.select(query);
    }

    @PutMapping
    public Integer updateUser(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping
    public Integer deleteUser(Integer id) {
        return userService.delete(id);
    }

    @GetMapping("/refreshtoken")
    public Map refreshToken(String refreshToken) {
        return userService.refreshToken(refreshToken);
    }
}
