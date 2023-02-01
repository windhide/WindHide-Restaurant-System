package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.annotation.RedisCache;
import com.windhide.employee.pojo.Activity;
import com.windhide.employee.service.ActivityService;
import com.windhide.employee.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author wuhaoye
* @description 针对表【activity】的数据库操作Service实现
* @createDate 2023-01-31 14:58:22
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{
    @RedisCache(targetClass = Activity.class)
    @Override
    public List<Activity> list() {
        return super.list();
    }
    @RedisCache(operation = "update")
    @Override
    public boolean updateById(Activity entity) {
        return super.updateById(entity);
    }

    @RedisCache(operation = "remove")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @RedisCache(operation = "insert")
    @Override
    public boolean save(Activity entity) {
        return super.save(entity);
    }
}




