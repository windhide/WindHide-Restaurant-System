package com.windhide.restaurant.pojo;

import com.windhide.restaurant.util.StateCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class T {

    private Enum state = StateCode.WARNING;
    private Object responeData;
    private String time;


}
