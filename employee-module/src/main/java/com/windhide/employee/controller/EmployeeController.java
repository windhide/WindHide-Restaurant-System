package com.windhide.employee.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("select")
    public T selectAllEmployee(@RequestBody HashMap<String,Integer> hashMap){
        Map<String,Object> dataMap = new HashMap<>();
        Page page = null;
        if(hashMap.get("pageNum") > 0){
            page = PageHelper.startPage(hashMap.get("pageNum"),hashMap.get("pageSize"));
        }
        PageInfo info = new PageInfo<>(page.getResult());
        dataMap.put("data",employeeService.list());
        dataMap.put("pageInfo",info);
        return new T(StateCode.SUCCESS,dataMap, TimeUtil.getNowTime());
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
