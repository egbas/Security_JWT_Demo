package com.egbas.springsecurityJWTdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String responseCode;
    private String responseMessage;
    private LoginInfo loginInfo;
}
