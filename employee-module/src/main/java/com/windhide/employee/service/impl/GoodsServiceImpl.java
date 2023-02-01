package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.annotation.RedisCache;
import com.windhide.employee.pojo.Goods;
import com.windhide.employee.service.GoodsService;
import com.windhide.employee.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2023-01-31 14:58:23
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

    @RedisCache(targetClass = Goods.class)
    @Override
    public List<Goods> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(Goods entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(Goods entity) {
        return super.save(entity);
    }

}




