package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.annotation.RedisCache;
import com.windhide.employee.pojo.Employee;
import com.windhide.employee.service.EmployeeService;
import com.windhide.employee.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2023-01-31 14:58:23
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

    @RedisCache(targetClass = Employee.class)
    @Override
    public List<Employee> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(Employee entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(Employee entity) {
        return super.save(entity);
    }

}




