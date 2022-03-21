package com.meta.backend.controller;


import com.meta.backend.dto.ResponseDto;
import com.meta.backend.dto.UserDto;
import com.meta.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseDto<UserDto> register(@RequestBody UserDto userDto) throws Exception {
        return ResponseDto.ok(userService.addUser(userDto));
    }
}
