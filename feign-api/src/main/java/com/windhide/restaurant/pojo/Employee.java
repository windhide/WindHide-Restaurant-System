package com.windhide.restaurant.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName employee
 */
@TableName(value = "employee")
@Data
public class Employee implements Serializable {
    /**
     * 员工id
     */
    @TableId(type = IdType.AUTO)
    private Integer employeeId;

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 员工照片
     */
    private String employeeImage;

    /**
     * 员工用户名
     */
    private String employeeUsername;

    /**
     * 员工密码
     */
    private String employeePassword;

    /**
     * 员工工资
     */
    private BigDecimal employeeSalary;

    /**
     * 员工等级id
     */
    private Integer employeeTypeId;

    @TableField(exist = false)
    private EmployeeType employeeType;

    /**
     * 创建时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}