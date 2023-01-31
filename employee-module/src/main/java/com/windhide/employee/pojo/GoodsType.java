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
 * @TableName goods_type
 */
@TableName(value ="goods_type")
@Data
public class GoodsType implements Serializable {
    /**
     * 菜品类型id
     */
    @TableId(type = IdType.AUTO)
    private Integer goods_type_id;

    /**
     * 菜品类型名
     */
    private String goods_type_name;

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
        GoodsType other = (GoodsType) that;
        return (this.getGoods_type_id() == null ? other.getGoods_type_id() == null : this.getGoods_type_id().equals(other.getGoods_type_id()))
            && (this.getGoods_type_name() == null ? other.getGoods_type_name() == null : this.getGoods_type_name().equals(other.getGoods_type_name()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGoods_type_id() == null) ? 0 : getGoods_type_id().hashCode());
        result = prime * result + ((getGoods_type_name() == null) ? 0 : getGoods_type_name().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goods_type_id=").append(goods_type_id);
        sb.append(", goods_type_name=").append(goods_type_name);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}