package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.annotation.RedisCache;
import com.windhide.employee.pojo.GoodsType;
import com.windhide.employee.service.GoodsTypeService;
import com.windhide.employee.mapper.GoodsTypeMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【goods_type】的数据库操作Service实现
* @createDate 2023-01-31 14:58:23
*/
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType>
    implements GoodsTypeService{

    @RedisCache(targetClass = GoodsType.class)
    @Override
    public List<GoodsType> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(GoodsType entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(GoodsType entity) {
        return super.save(entity);
    }
    
}




