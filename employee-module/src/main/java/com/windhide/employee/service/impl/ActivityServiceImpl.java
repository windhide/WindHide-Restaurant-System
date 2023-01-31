package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.pojo.Activity;
import com.windhide.employee.service.ActivityService;
import com.windhide.employee.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author wuhaoye
* @description 针对表【activity】的数据库操作Service实现
* @createDate 2023-01-31 14:58:22
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




