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
    private Integer employee_id;

    /**
     * 员工姓名
     */
    private String employee_name;

    /**
     * 员工照片
     */
    private String employee_image;

    /**
     * 员工用户名
     */
    private String employee_username;

    /**
     * 员工密码
     */
    private String employee_password;

    /**
     * 员工工资
     */
    private BigDecimal employee_salary;

    /**
     * 员工等级id
     */
    private Integer employee_type_id;

    /**
     * 创建时间
     */
    private Date create_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Employee other = (Employee) that;
        return (this.getEmployee_id() == null ? other.getEmployee_id() == null : this.getEmployee_id().equals(other.getEmployee_id()))
            && (this.getEmployee_name() == null ? other.getEmployee_name() == null : this.getEmployee_name().equals(other.getEmployee_name()))
            && (this.getEmployee_image() == null ? other.getEmployee_image() == null : this.getEmployee_image().equals(other.getEmployee_image()))
            && (this.getEmployee_username() == null ? other.getEmployee_username() == null : this.getEmployee_username().equals(other.getEmployee_username()))
            && (this.getEmployee_password() == null ? other.getEmployee_password() == null : this.getEmployee_password().equals(other.getEmployee_password()))
            && (this.getEmployee_salary() == null ? other.getEmployee_salary() == null : this.getEmployee_salary().equals(other.getEmployee_salary()))
            && (this.getEmployee_type_id() == null ? other.getEmployee_type_id() == null : this.getEmployee_type_id().equals(other.getEmployee_type_id()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployee_id() == null) ? 0 : getEmployee_id().hashCode());
        result = prime * result + ((getEmployee_name() == null) ? 0 : getEmployee_name().hashCode());
        result = prime * result + ((getEmployee_image() == null) ? 0 : getEmployee_image().hashCode());
        result = prime * result + ((getEmployee_username() == null) ? 0 : getEmployee_username().hashCode());
        result = prime * result + ((getEmployee_password() == null) ? 0 : getEmployee_password().hashCode());
        result = prime * result + ((getEmployee_salary() == null) ? 0 : getEmployee_salary().hashCode());
        result = prime * result + ((getEmployee_type_id() == null) ? 0 : getEmployee_type_id().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", employee_id=").append(employee_id);
        sb.append(", employee_name=").append(employee_name);
        sb.append(", employee_image=").append(employee_image);
        sb.append(", employee_username=").append(employee_username);
        sb.append(", employee_password=").append(employee_password);
        sb.append(", employee_salary=").append(employee_salary);
        sb.append(", employee_type_id=").append(employee_type_id);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}