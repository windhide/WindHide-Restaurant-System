package com.windhide.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    private Integer goodsTypeId;

    /**
     * 菜品类型名
     */
    private String goodsTypeName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}