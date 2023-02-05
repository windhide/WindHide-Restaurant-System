package com.windhide.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    private Integer goodsId;

    /**
     * 菜品名称
     */
    private String goodsName;

    /**
     * 菜品图片地址
     */
    private String goodsImage;

    /**
     * 菜品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 菜品折扣
     */
    private BigDecimal goodsDiscount;

    /**
     * 菜品类型id
     */
    private Integer goodsTypeId;

    @TableField(exist = false)
    private GoodsType goodsType;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}