package com.windhide.abonement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}