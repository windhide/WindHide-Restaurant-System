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
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer user_id;

    /**
     * 用户昵称
     */
    private String user_nickname;

    /**
     * 用户头像
     */
    private String user_image;

    /**
     * 用户手机号码
     */
    private String user_phone;

    /**
     * 用户性别
     */
    private String user_gender;

    /**
     * 用户登录名
     */
    private String user_name;

    /**
     * 用户密码
     */
    private String user_password;

    /**
     * 用户等级id
     */
    private Integer user_level_id;

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
        User other = (User) that;
        return (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getUser_nickname() == null ? other.getUser_nickname() == null : this.getUser_nickname().equals(other.getUser_nickname()))
            && (this.getUser_image() == null ? other.getUser_image() == null : this.getUser_image().equals(other.getUser_image()))
            && (this.getUser_phone() == null ? other.getUser_phone() == null : this.getUser_phone().equals(other.getUser_phone()))
            && (this.getUser_gender() == null ? other.getUser_gender() == null : this.getUser_gender().equals(other.getUser_gender()))
            && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
            && (this.getUser_password() == null ? other.getUser_password() == null : this.getUser_password().equals(other.getUser_password()))
            && (this.getUser_level_id() == null ? other.getUser_level_id() == null : this.getUser_level_id().equals(other.getUser_level_id()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getUser_nickname() == null) ? 0 : getUser_nickname().hashCode());
        result = prime * result + ((getUser_image() == null) ? 0 : getUser_image().hashCode());
        result = prime * result + ((getUser_phone() == null) ? 0 : getUser_phone().hashCode());
        result = prime * result + ((getUser_gender() == null) ? 0 : getUser_gender().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getUser_password() == null) ? 0 : getUser_password().hashCode());
        result = prime * result + ((getUser_level_id() == null) ? 0 : getUser_level_id().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", user_id=").append(user_id);
        sb.append(", user_nickname=").append(user_nickname);
        sb.append(", user_image=").append(user_image);
        sb.append(", user_phone=").append(user_phone);
        sb.append(", user_gender=").append(user_gender);
        sb.append(", user_name=").append(user_name);
        sb.append(", user_password=").append(user_password);
        sb.append(", user_level_id=").append(user_level_id);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}