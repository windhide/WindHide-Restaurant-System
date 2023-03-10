package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.annotation.RedisCache;
import com.windhide.employee.pojo.EmployeeType;
import com.windhide.employee.service.EmployeeTypeService;
import com.windhide.employee.mapper.EmployeeTypeMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【employee_type】的数据库操作Service实现
* @createDate 2023-01-31 14:58:23
*/
@Service
public class EmployeeTypeServiceImpl extends ServiceImpl<EmployeeTypeMapper, EmployeeType>
    implements EmployeeTypeService{

    @RedisCache(targetClass = EmployeeType.class)
    @Override
    public List<EmployeeType> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(EmployeeType entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(EmployeeType entity) {
        return super.save(entity);
    }

}




