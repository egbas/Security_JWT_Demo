package com.egbas.springsecurityJWTdemo.service;

import com.egbas.springsecurityJWTdemo.dto.AuthResponse;
import com.egbas.springsecurityJWTdemo.dto.LoginRequestDto;
import com.egbas.springsecurityJWTdemo.dto.LoginResponse;
import com.egbas.springsecurityJWTdemo.dto.RegisterRequestDto;
import org.springframework.stereotype.Service;

public interface UserService {
    AuthResponse registerUser(RegisterRequestDto registerRequestDto);

    LoginResponse login(LoginRequestDto loginRequestDto);
}
