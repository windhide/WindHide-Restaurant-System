package com.windhide.abonement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windhide.abonement.pojo.OrderDetail;
import com.windhide.abonement.service.OrderDetailService;
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
@RequestMapping("orderDetail")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @RequestMapping("select")
    public T selectAllOrder(@RequestBody HashMap<String, Integer> hashMap) {
        Map<String, Object> dataMap = new HashMap<>();
        //分页
        PageInfo info = new PageInfo<>(PageHelper.startPage(hashMap.get("pageNum"), hashMap.get("pageSize")).getResult());
        dataMap.put("data", orderDetailService.list());
        dataMap.put("pageInfo", info);
        return new T(StateCode.SUCCESS, orderDetailService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("select/{userId}")
    public T selectOrderByUserId(@PathVariable Integer userId){
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return new T(StateCode.SUCCESS, orderDetailService.list(queryWrapper), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateOrderById(@RequestBody OrderDetail orderDetail) {
        return new T(StateCode.SUCCESS, orderDetailService.updateById(orderDetail), TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{orderId}")
    public T removeOrderById(@PathVariable("orderId") int orderId){
        return new T(StateCode.SUCCESS, orderDetailService.removeById(orderId), TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody OrderDetail orderDetail) {
        System.out.println("----====---" + orderDetail);
        return new T(StateCode.SUCCESS, orderDetailService.save(orderDetail), TimeUtil.getNowTime());
    }

}
