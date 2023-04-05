package com.windhide.abonement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.abonement.annotation.RedisCache;
import com.windhide.abonement.pojo.Desk;
import com.windhide.abonement.pojo.Desk;
import com.windhide.abonement.service.DeskService;
import com.windhide.abonement.mapper.DeskMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【desk】的数据库操作Service实现
* @createDate 2023-04-05 13:43:57
*/
@Service
public class DeskServiceImpl extends ServiceImpl<DeskMapper, Desk>
    implements DeskService{
    @RedisCache(targetClass = Desk.class)
    @Override
    public List<Desk> list() {
        return super.list();
    }

    @RedisCache(operation = "update")
    @Override
    public boolean updateById(Desk entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(Desk entity) {
        return super.save(entity);
    }
}




