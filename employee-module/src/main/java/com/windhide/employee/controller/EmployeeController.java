package com.windhide.employee.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.employee.pojo.Employee;
import com.windhide.employee.service.EmployeeService;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("select")
    public T selectAllEmployee(@RequestBody int pageNum, @RequestBody int pageSize){
        if(pageNum > 0){
            PageHelper.startPage(pageNum,pageSize);
        }
        return new T(StateCode.SUCCESS,employeeService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateEmployeeById(@RequestBody Employee employee){
        return new T(StateCode.SUCCESS,employeeService.updateById(employee),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{employeeId}")
    public T removeEmployeeById(@PathVariable("employeeId") int employeeId){
        return new T(StateCode.SUCCESS,employeeService.removeById(employeeId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody Employee employee) {
        return new T(StateCode.SUCCESS,employeeService.save(employee), TimeUtil.getNowTime());
    }

}
