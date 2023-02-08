package com.windhide.abonement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping("select")
    public T selectAllShoppingCart(@RequestBody HashMap<String,Integer> hashMap){
        Map<String,Object> dataMap = new HashMap<>();
        //分页
        PageInfo info = new PageInfo<>(PageHelper.startPage(hashMap.get("pageNum"),hashMap.get("pageSize")).getResult());
        dataMap.put("data",shoppingCartService.list());
        dataMap.put("pageInfo",info);
        return new T(StateCode.SUCCESS,shoppingCartService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("select/{userId}")
    public T selectShoppingCartByUserId(@PathVariable Integer userId){
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return new T(StateCode.SUCCESS,shoppingCartService.list(queryWrapper), TimeUtil.getNowTime());
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
