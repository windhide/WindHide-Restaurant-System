package com.windhide.auth.respone;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String refreshToken;
    private String userId;
    private String userName;
    private String nickName;
    private String identity;

    private String identityType;

}