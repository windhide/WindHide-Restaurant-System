package com.windhide.employee.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.employee.pojo.IncomeState;
import com.windhide.employee.service.IncomeStateService;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("incomeState")
public class IncomeStateController {

    @Autowired
    IncomeStateService incomeStateService;

    @RequestMapping("select")
    public T selectAllIncomeState(@RequestBody int pageNum, @RequestBody int pageSize){
        if(pageNum > 0){
            PageHelper.startPage(pageNum,pageSize);
        }
        return new T(StateCode.SUCCESS,incomeStateService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateIncomeStateById(@RequestBody IncomeState incomeState){
        return new T(StateCode.SUCCESS,incomeStateService.updateById(incomeState),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{incomeStateId}")
    public T removeIncomeStateById(@PathVariable("incomeStateId") int incomeStateId){
        return new T(StateCode.SUCCESS,incomeStateService.removeById(incomeStateId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody IncomeState incomeState) {
        return new T(StateCode.SUCCESS,incomeStateService.save(incomeState), TimeUtil.getNowTime());
    }

}
