package com.windhide.abonement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.abonement.annotation.RedisCache;
import com.windhide.abonement.pojo.Order;
import com.windhide.abonement.service.OrderService;
import com.windhide.abonement.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-01-31 14:55:49
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{
    @RedisCache(targetClass = Order.class)
    @Override
    public List<Order> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(Order entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(Order entity) {
        return super.save(entity);
    }

}




