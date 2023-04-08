package com.windhide.abonement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName order
 */
@TableName(value = "order_detail")
@Data
public class OrderDetail implements Serializable {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单的数据json
     */
    private String orderDataJson;

    /**
     * 订单总金额
     */
    private Double orderPrice;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 餐桌id
     */
    private Integer deskId;

    @TableField(exist = false)
    private User user;

    /**
     * 创建时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}