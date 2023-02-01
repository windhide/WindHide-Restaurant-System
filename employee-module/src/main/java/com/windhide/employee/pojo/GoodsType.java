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
    private Integer goodsTypeId;

    /**
     * 菜品类型名
     */
    private String goodsTypeName;

    /**
     * 创建时间
     */
    private Date createTime;

}