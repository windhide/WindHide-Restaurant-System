package com.windhide.auth.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.windhide.auth.request.EmployeeRequest;
import com.windhide.auth.request.RefreshRequest;
import com.windhide.auth.request.UserRequest;
import com.windhide.auth.respone.LoginResponse;
import com.windhide.auth.respone.ResponseCodeEnum;
import com.windhide.auth.respone.ResponseResult;
import com.windhide.auth.util.JWTUtil;
import com.windhide.restaurant.client.EmployeeClient;
import com.windhide.restaurant.client.UserClient;
import com.windhide.restaurant.pojo.Employee;
import com.windhide.restaurant.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
public class LoginController {

    final static String TOKEN = "token";
    final static String REFRESH_TOKEN = "refreshToken";
    @Value("${secretKey:WindHide}")
    private String secretKey;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    EmployeeClient employeeClient;
    @Autowired
    UserClient userClient;

    @RequestMapping("/login/user")
    public ResponseResult userLogin(@RequestBody UserRequest userRequest) {
        User requestUser = new User();
        requestUser.setUserName(userRequest.getUserName());
        requestUser.setUserPassword(userRequest.getUserPassword());

        User databaseUser = userClient.queryUserByLogin(requestUser);

        try {
            if (databaseUser.getUserName() == null || databaseUser.getUserPassword() == null) {
                databaseUser.setUserName("");
                databaseUser.setUserPassword("");
            }
        } catch (Exception e) {
            return ResponseResult.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
        }

        if (databaseUser.getUserId() != null) {
            //  生成Token
            String token = JWTUtil.generateToken(databaseUser.getUserId() + "", databaseUser.getUserName(), secretKey);
            //  生成刷新Token
            String refreshToken = UUID.randomUUID().toString().replace("-", "");
            //  放入缓存
            HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
            String key = databaseUser.getUserId() + "";
            hashOperations.put(key, TOKEN, token);
            hashOperations.put(key, REFRESH_TOKEN, refreshToken);
            stringRedisTemplate.expire(key, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setRefreshToken(refreshToken);
            loginResponse.setUserId(databaseUser.getUserId() + "");
            loginResponse.setUserName(databaseUser.getUserName());
            loginResponse.setNickName(databaseUser.getUserNickname());
            loginResponse.setIdentity("user");
            loginResponse.setIdentityType(databaseUser.getUserLevel().getUserLevelName());
            return ResponseResult.success(loginResponse);
        }

        return ResponseResult.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
    }

    @RequestMapping("/login/employee")
    public ResponseResult login(@RequestBody EmployeeRequest request) {

        Employee requestEmployee = new Employee();
        requestEmployee.setEmployeeUsername(request.getEmployeeUsername());
        requestEmployee.setEmployeePassword(request.getEmployeePassword());
        Employee databaseEmployee = employeeClient.queryEmployeeByLogin(requestEmployee);

        try {
            if (databaseEmployee.getEmployeeUsername() == null || databaseEmployee.getEmployeePassword() == null) {
                databaseEmployee.setEmployeeUsername("");
                databaseEmployee.setEmployeePassword("");
                databaseEmployee.setEmployeeId(null);
            }
        } catch (Exception e) {
            return ResponseResult.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
        }
        if (databaseEmployee.getEmployeeId() != null) {
            //  生成Token
            String token = JWTUtil.generateToken(databaseEmployee.getEmployeeId() + "", databaseEmployee.getEmployeeUsername(), secretKey);

            //  生成刷新Token
            String refreshToken = UUID.randomUUID().toString().replace("-", "");

            //  放入缓存
            HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();

            String key = databaseEmployee.getEmployeeId() + "";
            hashOperations.put(key, TOKEN, token);
            hashOperations.put(key, REFRESH_TOKEN, refreshToken);
            stringRedisTemplate.expire(key, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setRefreshToken(refreshToken);
            loginResponse.setUserId(databaseEmployee.getEmployeeId() + "");
            loginResponse.setUserName(databaseEmployee.getEmployeeUsername());
            loginResponse.setNickName(databaseEmployee.getEmployeeName());
            loginResponse.setIdentity("employee");
            loginResponse.setIdentityType(databaseEmployee.getEmployeeType().getEmployeeTypeName());

            return ResponseResult.success(loginResponse);
        }

        return ResponseResult.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
    }

    @GetMapping("/logout")
    public ResponseResult logout(@RequestParam("userId") String userId) {
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String key = userId;
        hashOperations.delete(key, TOKEN);
        return ResponseResult.success();
    }

    @PostMapping("/refreshToken")
    public ResponseResult refreshToken(@RequestBody @Validated RefreshRequest request) {
        String userId = request.getUserId();
        //通过userId去数据库查到userName
        String userName = "WindHide";
        String refreshToken = request.getRefreshToken();
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String key = userId;
        String originalRefreshToken = hashOperations.get(key, REFRESH_TOKEN);
        if (StringUtils.isBlank(originalRefreshToken) || !originalRefreshToken.equals(refreshToken)) {
            return ResponseResult.error(ResponseCodeEnum.REFRESH_TOKEN_INVALID.getCode(), ResponseCodeEnum.REFRESH_TOKEN_INVALID.getMessage());
        }

        //  生成新token
        String newToken = JWTUtil.generateToken(userId, userName, secretKey);
        hashOperations.put(key, TOKEN, newToken);
        stringRedisTemplate.expire(userId, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

        return ResponseResult.success(newToken);
    }
}