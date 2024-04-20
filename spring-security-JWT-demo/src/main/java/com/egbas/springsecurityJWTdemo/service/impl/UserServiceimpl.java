package com.egbas.springsecurityJWTdemo.service.impl;

import com.egbas.springsecurityJWTdemo.config.JwtService;
import com.egbas.springsecurityJWTdemo.dto.*;
import com.egbas.springsecurityJWTdemo.entity.UserEntity;
import com.egbas.springsecurityJWTdemo.enums.Role;
import com.egbas.springsecurityJWTdemo.repository.UserRepository;
import com.egbas.springsecurityJWTdemo.service.EmailSenderService;
import com.egbas.springsecurityJWTdemo.service.UserService;
import com.egbas.springsecurityJWTdemo.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSenderService emailSenderService;
    @Override
    public AuthResponse registerUser(RegisterRequestDto registerRequestDto) {
        UserEntity userEntity = UserEntity.builder()
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .email(registerRequestDto.getEmail())
                .password(encoder.encode(registerRequestDto.getPassword()))
                .role(Role.USER)
                .build();

        UserEntity saveduser = userRepository.save(userEntity);
        //send email alert

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(saveduser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("Congratulations, Your account has been succesfully created")
                .build();

        emailSenderService.sendEmailAlert(emailDetails);

        var jwtToken = jwtService.generateToken(userEntity);
        return AuthResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .registrationInfo(RegistrationInfo.builder()
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .email(userEntity.getEmail())
                        .token(jwtToken)
                        .build())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequestDto loginRequestDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        UserEntity userEntity = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow();


        var jwtToken = jwtService.generateToken(userEntity);
        return LoginResponse.builder()
                .responseCode(AccountUtils.LOGIN_SUCCESS_CODE)
                .responseMessage(AccountUtils.LOGIN_SUCCESS_MESSAGE)
                .loginInfo(LoginInfo.builder()
                        .email(userEntity.getEmail())
                        .token(jwtToken)
                        .build())
                .build();
    }
}
