package com.windhide.abonement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windhide.abonement.pojo.User;
import com.windhide.abonement.service.UserService;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("select")
    public T selectAllUser(@RequestBody HashMap<String,Integer> hashMap){
        Map<String,Object> dataMap = new HashMap<>();
        //分页
        PageInfo info = new PageInfo<>(PageHelper.startPage(hashMap.get("pageNum"),hashMap.get("pageSize")).getResult());
        dataMap.put("data",userService.list());
        dataMap.put("pageInfo",info);
        return new T(StateCode.SUCCESS,userService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("select/{userId}")
    public T selectUserById(@PathVariable Integer userId){
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return new T(StateCode.SUCCESS,userService.list(queryWrapper), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateUserById(@RequestBody User user){
        return new T(StateCode.SUCCESS,userService.updateById(user),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{userId}")
    public T removeUserById(@PathVariable("userId") int userId){
        return new T(StateCode.SUCCESS,userService.removeById(userId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody User user) {
        return new T(StateCode.SUCCESS,userService.save(user), TimeUtil.getNowTime());
    }

}
