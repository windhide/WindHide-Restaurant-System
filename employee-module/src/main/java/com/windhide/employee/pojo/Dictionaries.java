package com.windhide.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dictionaries
 */
@TableName(value ="dictionaries")
@Data
public class Dictionaries implements Serializable {
    /**
     * 字典id
     */
    @TableId(type = IdType.AUTO)
    private Integer dictionaries_id;

    /**
     * 字典名称
     */
    private String dictionaries_name;

    /**
     * 字典eng
     */
    private String dictionaries_varName;

    /**
     * 数量
     */
    private Integer variable;

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
        Dictionaries other = (Dictionaries) that;
        return (this.getDictionaries_id() == null ? other.getDictionaries_id() == null : this.getDictionaries_id().equals(other.getDictionaries_id()))
            && (this.getDictionaries_name() == null ? other.getDictionaries_name() == null : this.getDictionaries_name().equals(other.getDictionaries_name()))
            && (this.getDictionaries_varName() == null ? other.getDictionaries_varName() == null : this.getDictionaries_varName().equals(other.getDictionaries_varName()))
            && (this.getVariable() == null ? other.getVariable() == null : this.getVariable().equals(other.getVariable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDictionaries_id() == null) ? 0 : getDictionaries_id().hashCode());
        result = prime * result + ((getDictionaries_name() == null) ? 0 : getDictionaries_name().hashCode());
        result = prime * result + ((getDictionaries_varName() == null) ? 0 : getDictionaries_varName().hashCode());
        result = prime * result + ((getVariable() == null) ? 0 : getVariable().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dictionaries_id=").append(dictionaries_id);
        sb.append(", dictionaries_name=").append(dictionaries_name);
        sb.append(", dictionaries_varName=").append(dictionaries_varName);
        sb.append(", variable=").append(variable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}