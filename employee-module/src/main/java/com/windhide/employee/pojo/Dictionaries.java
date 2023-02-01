package com.windhide.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dictionaries
 */
@TableName(value ="dictionaries")
@Data
public class Dictionaries implements Serializable {
    /**
     * 字典id
     */
    @TableId(type = IdType.AUTO)
    private Integer dictionariesId;

    /**
     * 字典名称
     */
    private String dictionariesName;

    /**
     * 字典eng
     */
    private String dictionariesVarName;

    /**
     * 数量
     */
    private Integer variable;

}