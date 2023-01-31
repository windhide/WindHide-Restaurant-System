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
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods implements Serializable {
    /**
     * 菜品id
     */
    @TableId(type = IdType.AUTO)
    private Integer goods_id;

    /**
     * 菜品名称
     */
    private String goods_name;

    /**
     * 菜品图片地址
     */
    private String goods_image;

    /**
     * 菜品价格
     */
    private BigDecimal goods_price;

    /**
     * 菜品折扣
     */
    private BigDecimal goods_discount;

    /**
     * 菜品类型id
     */
    private Integer goods_type_id;

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
        Goods other = (Goods) that;
        return (this.getGoods_id() == null ? other.getGoods_id() == null : this.getGoods_id().equals(other.getGoods_id()))
            && (this.getGoods_name() == null ? other.getGoods_name() == null : this.getGoods_name().equals(other.getGoods_name()))
            && (this.getGoods_image() == null ? other.getGoods_image() == null : this.getGoods_image().equals(other.getGoods_image()))
            && (this.getGoods_price() == null ? other.getGoods_price() == null : this.getGoods_price().equals(other.getGoods_price()))
            && (this.getGoods_discount() == null ? other.getGoods_discount() == null : this.getGoods_discount().equals(other.getGoods_discount()))
            && (this.getGoods_type_id() == null ? other.getGoods_type_id() == null : this.getGoods_type_id().equals(other.getGoods_type_id()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGoods_id() == null) ? 0 : getGoods_id().hashCode());
        result = prime * result + ((getGoods_name() == null) ? 0 : getGoods_name().hashCode());
        result = prime * result + ((getGoods_image() == null) ? 0 : getGoods_image().hashCode());
        result = prime * result + ((getGoods_price() == null) ? 0 : getGoods_price().hashCode());
        result = prime * result + ((getGoods_discount() == null) ? 0 : getGoods_discount().hashCode());
        result = prime * result + ((getGoods_type_id() == null) ? 0 : getGoods_type_id().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goods_id=").append(goods_id);
        sb.append(", goods_name=").append(goods_name);
        sb.append(", goods_image=").append(goods_image);
        sb.append(", goods_price=").append(goods_price);
        sb.append(", goods_discount=").append(goods_discount);
        sb.append(", goods_type_id=").append(goods_type_id);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}