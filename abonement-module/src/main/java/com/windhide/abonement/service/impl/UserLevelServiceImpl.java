package com.windhide.abonement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.abonement.annotation.RedisCache;
import com.windhide.abonement.pojo.UserLevel;
import com.windhide.abonement.service.UserLevelService;
import com.windhide.abonement.mapper.UserLevelMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【user_level】的数据库操作Service实现
* @createDate 2023-01-31 14:55:49
*/
@Service
public class UserLevelServiceImpl extends ServiceImpl<UserLevelMapper, UserLevel>
    implements UserLevelService{

    @RedisCache(targetClass = UserLevel.class)
    @Override
    public List<UserLevel> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(UserLevel entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(UserLevel entity) {
        return super.save(entity);
    }
}




