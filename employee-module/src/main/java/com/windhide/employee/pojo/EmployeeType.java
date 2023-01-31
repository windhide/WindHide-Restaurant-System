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
    private Integer employee_type_id;

    /**
     * 员工等级描述
     */
    private String employee_type_name;

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
        EmployeeType other = (EmployeeType) that;
        return (this.getEmployee_type_id() == null ? other.getEmployee_type_id() == null : this.getEmployee_type_id().equals(other.getEmployee_type_id()))
            && (this.getEmployee_type_name() == null ? other.getEmployee_type_name() == null : this.getEmployee_type_name().equals(other.getEmployee_type_name()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployee_type_id() == null) ? 0 : getEmployee_type_id().hashCode());
        result = prime * result + ((getEmployee_type_name() == null) ? 0 : getEmployee_type_name().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", employee_type_id=").append(employee_type_id);
        sb.append(", employee_type_name=").append(employee_type_name);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}