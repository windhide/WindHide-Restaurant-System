package com.windhide.abonement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user_level
 */
@TableName(value ="user_level")
@Data
public class UserLevel implements Serializable {
    /**
     * 用户等级id
     */
    @TableId(type = IdType.AUTO)
    private Integer userLevelId;

    /**
     * 用户等级名称
     */
    private String userLevelName;

    /**
     * 创建时间
     */
    private Date createTime;
}