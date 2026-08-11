package com.example.demo.controller;

import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserSaveRequest;
import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse kullaniciOlustur(@RequestBody UserSaveRequest userSaveRequest) {

        return userService.kullaniciOlustur(userSaveRequest);

    }

    @GetMapping
    public List<UserResponse> tumKullanicilar() {

        return userService.tumKullanicilar();

    }

    @GetMapping("/{id}")
    public User kullaniciBul(@PathVariable UUID id) {

        return userService.kullaniciBul(id);

    }
}
