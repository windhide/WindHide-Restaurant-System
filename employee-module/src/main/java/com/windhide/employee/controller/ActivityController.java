package com.windhide.employee.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.employee.pojo.Activity;
import com.windhide.employee.service.ActivityService;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @RequestMapping("select")
    public T selectAllActivity(@RequestBody int pageNum, @RequestBody int pageSize){
        if(pageNum > 0){
            PageHelper.startPage(pageNum,pageSize);
        }
        return new T(StateCode.SUCCESS,activityService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateActivityById(@RequestBody Activity activity){
        return new T(StateCode.SUCCESS,activityService.updateById(activity),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{activityId}")
    public T removeActivityById(@PathVariable("activityId") int activityId){
        return new T(StateCode.SUCCESS,activityService.removeById(activityId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody Activity activity) {
        return new T(StateCode.SUCCESS,activityService.save(activity), TimeUtil.getNowTime());
    }

}
