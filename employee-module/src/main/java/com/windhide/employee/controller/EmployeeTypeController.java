package com.windhide.employee.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windhide.employee.pojo.EmployeeType;
import com.windhide.employee.service.EmployeeTypeService;
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
@RequestMapping("employeeType")
public class EmployeeTypeController {

    @Autowired
    EmployeeTypeService employeeTypeService;

    @RequestMapping("select")
    public T selectAllEmployeeType(@RequestBody HashMap<String,Integer> hashMap){
        Map<String,Object> dataMap = new HashMap<>();
        Page page = null;
        if(hashMap.get("pageNum") > 0){
            page = PageHelper.startPage(hashMap.get("pageNum"),hashMap.get("pageSize"));
        }
        PageInfo info = new PageInfo<>(page.getResult());
        dataMap.put("data",employeeTypeService.list());
        dataMap.put("pageInfo",info);
        return new T(StateCode.SUCCESS,dataMap, TimeUtil.getNowTime());
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
