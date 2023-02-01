package com.windhide.employee.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.employee.pojo.EmployeeType;
import com.windhide.employee.service.impl.EmployeeTypeServiceImpl;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employeeType")
public class EmployeeTypeController {

    @Autowired
    EmployeeTypeServiceImpl employeeTypeService;

    @RequestMapping("select")
    public T selectAllEmployeeType(@RequestBody int pageNum, @RequestBody int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new T(StateCode.SUCCESS,employeeTypeService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateEmployeeTypeById(@RequestBody EmployeeType employeeType){
        return new T(StateCode.SUCCESS,employeeTypeService.updateById(employeeType),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{employeeTypeId}")
    public T removeEmployeeTypeById(@PathVariable("employeeTypeId") int employeeTypeId){
        return new T(StateCode.SUCCESS,employeeTypeService.removeById(employeeTypeId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody EmployeeType employeeType) {
        return new T(StateCode.SUCCESS,employeeTypeService.save(employeeType), TimeUtil.getNowTime());
    }

}
