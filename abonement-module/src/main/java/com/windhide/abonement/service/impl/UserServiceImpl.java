package com.windhide.abonement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.abonement.annotation.RedisCache;
import com.windhide.abonement.pojo.User;
import com.windhide.abonement.service.UserService;
import com.windhide.abonement.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-01-31 14:55:49
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @RedisCache(targetClass = User.class)
    @Override
    public List<User> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(User entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

}




