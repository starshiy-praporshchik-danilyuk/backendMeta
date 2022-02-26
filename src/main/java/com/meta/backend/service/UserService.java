package com.meta.backend.service;

import com.meta.backend.converter.UserConverter;
import com.meta.backend.dto.UserDto;
import com.meta.backend.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean addUser(UserDto newUser) throws Exception {
        if(userRepo.existsByUsername(newUser.getUsername()))
            throw new Exception("USERNAME_ALREADY_EXISTS");
        userRepo.save(UserConverter.toEntity(newUser));
        return true;
    }
}
