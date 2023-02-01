package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.annotation.RedisCache;
import com.windhide.employee.pojo.Dictionaries;
import com.windhide.employee.pojo.Dictionaries;
import com.windhide.employee.service.DictionariesService;
import com.windhide.employee.mapper.DictionariesMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【dictionaries】的数据库操作Service实现
* @createDate 2023-01-31 14:58:23
*/
@Service
public class DictionariesServiceImpl extends ServiceImpl<DictionariesMapper, Dictionaries>
    implements DictionariesService{

    @RedisCache(targetClass = Dictionaries.class)
    @Override
    public List<Dictionaries> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(Dictionaries entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(Dictionaries entity) {
        return super.save(entity);
    }
    
}




