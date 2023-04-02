package com.windhide.abonement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.windhide.abonement.pojo.ShoppingCart;
import com.windhide.abonement.service.ShoppingCartService;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

//    @RequestMapping("select")
//    public T selectAllShoppingCart(){
//        Map<String,Object> dataMap = new HashMap<>();
//        dataMap.put("data",shoppingCartService.list());
//        return new T(StateCode.SUCCESS,shoppingCartService.list(), TimeUtil.getNowTime());
//    }

    @RequestMapping("select/{userId}")
    public T selectShoppingCartByUserId(@PathVariable Integer userId){
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return new T(StateCode.SUCCESS,shoppingCartService.list(queryWrapper), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateShoppingCartById(@RequestBody ShoppingCart shoppingCart) {
        return new T(StateCode.SUCCESS, shoppingCartService.updateById(shoppingCart), TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{userId}")
    public T removeShoppingCartById(@PathVariable("userId") int userId){
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return new T(StateCode.SUCCESS,shoppingCartService.remove(queryWrapper),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody ShoppingCart shoppingCart) {
        return new T(StateCode.SUCCESS, shoppingCartService.save(shoppingCart), TimeUtil.getNowTime());
    }

}
