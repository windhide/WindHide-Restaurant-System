package com.windhide.abonement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.abonement.annotation.RedisCache;
import com.windhide.abonement.pojo.ShoppingCart;
import com.windhide.abonement.pojo.ShoppingCart;
import com.windhide.abonement.service.ShoppingCartService;
import com.windhide.abonement.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【shopping_cart】的数据库操作Service实现
* @createDate 2023-01-31 14:55:49
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{
    @RedisCache(targetClass = ShoppingCart.class)
    @Override
    public List<ShoppingCart> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(ShoppingCart entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(ShoppingCart entity) {
        return super.save(entity);
    }
}




