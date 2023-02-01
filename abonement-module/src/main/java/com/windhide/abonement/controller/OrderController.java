package com.windhide.abonement.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.abonement.pojo.Order;
import com.windhide.abonement.service.OrderService;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("select")
    public T selectAllOrder(@RequestBody int pageNum,@RequestBody int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new T(StateCode.SUCCESS,orderService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateOrderById(@RequestBody Order order){
        return new T(StateCode.SUCCESS,orderService.updateById(order),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{orderId}")
    public T removeOrderById(@PathVariable("orderId") int orderId){
        return new T(StateCode.SUCCESS,orderService.removeById(orderId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody Order order) {
        return new T(StateCode.SUCCESS,orderService.save(order), TimeUtil.getNowTime());
    }

}
