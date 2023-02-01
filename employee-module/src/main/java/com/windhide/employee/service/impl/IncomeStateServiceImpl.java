package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.annotation.RedisCache;
import com.windhide.employee.pojo.IncomeState;
import com.windhide.employee.service.IncomeStateService;
import com.windhide.employee.mapper.IncomeStateMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【income_state】的数据库操作Service实现
* @createDate 2023-01-31 14:58:23
*/
@Service
public class IncomeStateServiceImpl extends ServiceImpl<IncomeStateMapper, IncomeState>
    implements IncomeStateService{

    @RedisCache(targetClass = IncomeState.class)
    @Override
    public List<IncomeState> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(IncomeState entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(IncomeState entity) {
        return super.save(entity);
    }
    
}




