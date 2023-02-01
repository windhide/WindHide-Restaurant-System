package com.windhide.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName income_state
 */
@TableName(value ="income_state")
@Data
public class IncomeState implements Serializable {
    /**
     * 报表id
     */
    @TableId(type = IdType.AUTO)
    private Integer incomeStateId;

    /**
     * 报表年份
     */
    private Integer incomeStateYear;

    /**
     * 报表月份
     */
    private Integer incomeStateMonth;

    /**
     * 报表金额
     */
    private BigDecimal incomeStateMoney;
}