package com.windhide.abonement.controller;

import com.github.pagehelper.PageHelper;
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

    @RequestMapping("select")
    public T selectAllShoppingCart(@RequestBody int pageNum,@RequestBody int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new T(StateCode.SUCCESS,shoppingCartService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateShoppingCartById(@RequestBody ShoppingCart shoppingCart){
        return new T(StateCode.SUCCESS,shoppingCartService.updateById(shoppingCart),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{shoppingCartId}")
    public T removeShoppingCartById(@PathVariable("shoppingCartId") int shoppingCartId){
        return new T(StateCode.SUCCESS,shoppingCartService.removeById(shoppingCartId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody ShoppingCart shoppingCart) {
        return new T(StateCode.SUCCESS,shoppingCartService.save(shoppingCart), TimeUtil.getNowTime());
    }

}
