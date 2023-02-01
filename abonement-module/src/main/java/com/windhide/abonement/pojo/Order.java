package com.windhide.abonement.pojo;

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
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单的数据json
     */
    private Object orderDataJson;

    /**
     * 订单总金额
     */
    private BigDecimal orderPrice;

    /**
     * 用户id
     */
    @TableField(exist = false)
    private User user;
    /**
     * 创建时间
     */
    private Date createTime;

}