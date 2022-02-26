package com.meta.backend.controller;


import com.meta.backend.dto.UserDto;
import com.meta.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public boolean register(@RequestBody UserDto userDto) throws Exception {
        return userService.addUser(userDto);
    }
}
