package com.windhide.abonement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName desk
 */
@TableName(value ="desk")
@Data
public class Desk implements Serializable {
    /**
     * 餐桌主键
     */
    @TableId(type = IdType.AUTO)
    private Integer deskId;

    /**
     * 餐桌类型
     * 1.小桌
     * 2.中桌
     * 3.大桌
     */
    private Integer deskType;

    /**
     * 是否被使用
     0.未被使用
     1.使用中
     2.被预约了
     */
    private Integer isUse;
}