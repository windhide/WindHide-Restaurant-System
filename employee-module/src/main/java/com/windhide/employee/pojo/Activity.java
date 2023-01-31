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
 * @TableName activity
 */
@TableName(value ="activity")
@Data
public class Activity implements Serializable {
    /**
     * 活动id
     */
    @TableId(type = IdType.AUTO)
    private Integer activity_id;

    /**
     * 活动名称
     */
    private String activity_name;

    /**
     * 活动折扣
     */
    private BigDecimal activity_discount;

    /**
     * 活动开始时间
     */
    private Date activity_start_time;

    /**
     * 活动结束时间
     */
    private Date activity_end_time;

    /**
     * 
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
        Activity other = (Activity) that;
        return (this.getActivity_id() == null ? other.getActivity_id() == null : this.getActivity_id().equals(other.getActivity_id()))
            && (this.getActivity_name() == null ? other.getActivity_name() == null : this.getActivity_name().equals(other.getActivity_name()))
            && (this.getActivity_discount() == null ? other.getActivity_discount() == null : this.getActivity_discount().equals(other.getActivity_discount()))
            && (this.getActivity_start_time() == null ? other.getActivity_start_time() == null : this.getActivity_start_time().equals(other.getActivity_start_time()))
            && (this.getActivity_end_time() == null ? other.getActivity_end_time() == null : this.getActivity_end_time().equals(other.getActivity_end_time()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActivity_id() == null) ? 0 : getActivity_id().hashCode());
        result = prime * result + ((getActivity_name() == null) ? 0 : getActivity_name().hashCode());
        result = prime * result + ((getActivity_discount() == null) ? 0 : getActivity_discount().hashCode());
        result = prime * result + ((getActivity_start_time() == null) ? 0 : getActivity_start_time().hashCode());
        result = prime * result + ((getActivity_end_time() == null) ? 0 : getActivity_end_time().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activity_id=").append(activity_id);
        sb.append(", activity_name=").append(activity_name);
        sb.append(", activity_discount=").append(activity_discount);
        sb.append(", activity_start_time=").append(activity_start_time);
        sb.append(", activity_end_time=").append(activity_end_time);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}