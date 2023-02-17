package com.windhide.restaurant.client;

import com.windhide.restaurant.pojo.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "employee-module")
public interface EmployeeClient {

    @RequestMapping("employee/employeeLogin")
    Employee queryEmployeeByLogin(@RequestBody Employee employee);
}
