package com.windhide.abonement.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.abonement.pojo.UserLevel;
import com.windhide.abonement.service.UserLevelService;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userLevel")
public class UserLevelController {

    @Autowired
    UserLevelService userLevelService;

    @RequestMapping("select")
    public T selectAllUserLevel(@RequestBody int pageNum,@RequestBody int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new T(StateCode.SUCCESS,userLevelService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateUserLevelById(@RequestBody UserLevel userLevel){
        return new T(StateCode.SUCCESS,userLevelService.updateById(userLevel),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{userLevelId}")
    public T removeUserLevelById(@PathVariable("userLevelId") int userLevelId){
        return new T(StateCode.SUCCESS,userLevelService.removeById(userLevelId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody UserLevel userLevel) {
        return new T(StateCode.SUCCESS,userLevelService.save(userLevel), TimeUtil.getNowTime());
    }

}
