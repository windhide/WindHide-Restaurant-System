package com.windhide.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName employee
 */
@TableName(value ="employee")
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
    private Date createTime;

}