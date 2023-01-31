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
    private Integer income_state_id;

    /**
     * 报表年份
     */
    private Integer income_state_year;

    /**
     * 报表月份
     */
    private Integer income_state_month;

    /**
     * 报表金额
     */
    private BigDecimal income_state_money;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        IncomeState other = (IncomeState) that;
        return (this.getIncome_state_id() == null ? other.getIncome_state_id() == null : this.getIncome_state_id().equals(other.getIncome_state_id()))
            && (this.getIncome_state_year() == null ? other.getIncome_state_year() == null : this.getIncome_state_year().equals(other.getIncome_state_year()))
            && (this.getIncome_state_month() == null ? other.getIncome_state_month() == null : this.getIncome_state_month().equals(other.getIncome_state_month()))
            && (this.getIncome_state_money() == null ? other.getIncome_state_money() == null : this.getIncome_state_money().equals(other.getIncome_state_money()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIncome_state_id() == null) ? 0 : getIncome_state_id().hashCode());
        result = prime * result + ((getIncome_state_year() == null) ? 0 : getIncome_state_year().hashCode());
        result = prime * result + ((getIncome_state_month() == null) ? 0 : getIncome_state_month().hashCode());
        result = prime * result + ((getIncome_state_money() == null) ? 0 : getIncome_state_money().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", income_state_id=").append(income_state_id);
        sb.append(", income_state_year=").append(income_state_year);
        sb.append(", income_state_month=").append(income_state_month);
        sb.append(", income_state_money=").append(income_state_money);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}