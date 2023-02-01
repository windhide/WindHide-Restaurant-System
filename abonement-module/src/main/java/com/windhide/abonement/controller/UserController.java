package com.windhide.abonement.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.abonement.pojo.ShoppingCart;
import com.windhide.abonement.pojo.User;
import com.windhide.abonement.service.impl.UserServiceImpl;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("select")
    public T selectAllUser(@RequestBody int pageNum,@RequestBody int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new T(StateCode.SUCCESS,userService.list(), TimeUtil.getNowTime());
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
