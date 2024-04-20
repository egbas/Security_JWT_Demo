package com.egbas.springsecurityJWTdemo.controller;

import com.egbas.springsecurityJWTdemo.dto.AuthResponse;
import com.egbas.springsecurityJWTdemo.dto.LoginRequestDto;
import com.egbas.springsecurityJWTdemo.dto.LoginResponse;
import com.egbas.springsecurityJWTdemo.dto.RegisterRequestDto;
import com.egbas.springsecurityJWTdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(userService.registerUser(registerRequestDto));
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }

}
