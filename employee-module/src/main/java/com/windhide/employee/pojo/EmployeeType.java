package com.windhide.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
    private Date createTime;

}