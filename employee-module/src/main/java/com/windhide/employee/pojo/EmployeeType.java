package com.windhide.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName employee_type
 */
@TableName(value ="employee_type")
@Data
public class EmployeeType implements Serializable {
    /**
     * 员工等级id
     */
    @TableId(type = IdType.AUTO)
    private Integer employeeTypeId;

    /**
     * 员工等级描述
     */
    private String employeeTypeName;

    /**
     * 创建时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}