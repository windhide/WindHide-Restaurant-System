package com.windhide.abonement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.abonement.annotation.RedisCache;
import com.windhide.abonement.mapper.OrderDetailMapper;
import com.windhide.abonement.pojo.OrderDetail;
import com.windhide.abonement.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuhaoye
 * @description 针对表【order】的数据库操作Service实现
 * @createDate 2023-01-31 14:55:49
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
        implements OrderDetailService {
    @RedisCache(targetClass = OrderDetail.class)
    @Override
    public List<OrderDetail> list() {
        return super.list();
    }

    @RedisCache(operation = "update")
    @Override
    public boolean updateById(OrderDetail entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(OrderDetail entity) {
        return super.save(entity);
    }

}




