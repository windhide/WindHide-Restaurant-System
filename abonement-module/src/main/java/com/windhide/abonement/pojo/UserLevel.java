package com.windhide.abonement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user_level
 */
@TableName(value ="user_level")
@Data
public class UserLevel implements Serializable {
    /**
     * 用户等级id
     */
    @TableId(type = IdType.AUTO)
    private Integer user_level_id;

    /**
     * 用户等级名称
     */
    private String user_level_name;

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
        UserLevel other = (UserLevel) that;
        return (this.getUser_level_id() == null ? other.getUser_level_id() == null : this.getUser_level_id().equals(other.getUser_level_id()))
            && (this.getUser_level_name() == null ? other.getUser_level_name() == null : this.getUser_level_name().equals(other.getUser_level_name()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUser_level_id() == null) ? 0 : getUser_level_id().hashCode());
        result = prime * result + ((getUser_level_name() == null) ? 0 : getUser_level_name().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", user_level_id=").append(user_level_id);
        sb.append(", user_level_name=").append(user_level_name);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}