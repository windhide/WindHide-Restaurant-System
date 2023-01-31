package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.pojo.Employee;
import com.windhide.employee.service.EmployeeService;
import com.windhide.employee.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author wuhaoye
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2023-01-31 14:58:23
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




