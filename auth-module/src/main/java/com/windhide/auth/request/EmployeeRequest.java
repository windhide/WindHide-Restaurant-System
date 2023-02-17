package com.windhide.auth.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    /**
     * 员工用户名
     */
    private String employeeUsername;

    /**
     * 员工密码
     */
    private String employeePassword;
}
