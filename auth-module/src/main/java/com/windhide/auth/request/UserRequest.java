package com.windhide.auth.request;


import lombok.Data;

@Data
public class UserRequest {
    /**
     * 用户登录名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;
}
