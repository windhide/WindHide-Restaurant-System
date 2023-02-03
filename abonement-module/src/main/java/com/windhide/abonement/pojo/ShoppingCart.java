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
 * 
 * @TableName shopping_cart
 */
@TableName(value ="shopping_cart")
@Data
public class ShoppingCart implements Serializable {
    /**
     * 购物车id
     */
    @TableId(type = IdType.AUTO)
    private Integer shoppingCartId;

    /**
     * 购物车的json内容
     */
    private Object shoppingCartDataJson;

    /**
     * 用户id
     */
    private Integer userId;

    @TableField(exist = false)
    private User user;

    /**
     * 创建时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}