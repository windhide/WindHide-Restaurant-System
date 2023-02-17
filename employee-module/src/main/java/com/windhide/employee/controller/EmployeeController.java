package com.windhide.employee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windhide.employee.pojo.Employee;
import com.windhide.employee.pojo.EmployeeType;
import com.windhide.employee.service.EmployeeService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeTypeService employeeTypeService;

    Map<Integer, EmployeeType> employeeTypeMap;

    @RequestMapping("select")
    public T selectAllEmployee(@RequestBody HashMap<String,Integer> hashMap){
        Map<String,Object> dataMap = new HashMap<>();
        //分页
        PageInfo info = new PageInfo<>(PageHelper.startPage(hashMap.get("pageNum"),hashMap.get("pageSize")).getResult());
        employeeTypeInit();
        List<Employee> employeeList = employeeService.list();
        employeeList.replaceAll(this::employeeTypeInit);
        dataMap.put("data",employeeList);
        dataMap.put("pageInfo",info);
        return new T(StateCode.SUCCESS,dataMap, TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateEmployeeById(@RequestBody Employee employee){
        return new T(StateCode.SUCCESS,employeeService.updateById(employee),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{employeeId}")
    public T removeEmployeeById(@PathVariable("employeeId") int employeeId) {
        return new T(StateCode.SUCCESS, employeeService.removeById(employeeId), TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody Employee employee) {
        return new T(StateCode.SUCCESS, employeeService.save(employee), TimeUtil.getNowTime());
    }

    @RequestMapping("employeeLogin")
    public Employee queryEmployeeByLogin(@RequestBody Employee employee) {
        return employeeService.getOne(new QueryWrapper<>(employee));
    }

    /**
     * 加载类型
     */
    public void employeeTypeInit() {
        employeeTypeMap = employeeTypeService.list().stream().collect(Collectors.toMap(EmployeeType::getEmployeeTypeId, employeeType -> employeeType));
    }

    /**
     * 植入类型
     *
     * @param employee
     * @return
     */
    public Employee employeeTypeInit(Employee employee) {
        EmployeeType tempEmployeeType = employeeTypeMap.get(employee.getEmployeeTypeId());
        employee.setEmployeeType(tempEmployeeType);
        return employee;
    }

}
