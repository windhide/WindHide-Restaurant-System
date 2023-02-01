package com.windhide.employee.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.employee.pojo.Goods;
import com.windhide.employee.service.impl.GoodsServiceImpl;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsServiceImpl goodsService;

    @RequestMapping("select")
    public T selectAllGoods(@RequestBody int pageNum, @RequestBody int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new T(StateCode.SUCCESS,goodsService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateGoodsById(@RequestBody Goods goods){
        return new T(StateCode.SUCCESS,goodsService.updateById(goods),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{goodsId}")
    public T removeGoodsById(@PathVariable("goodsId") int goodsId){
        return new T(StateCode.SUCCESS,goodsService.removeById(goodsId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody Goods goods) {
        return new T(StateCode.SUCCESS,goodsService.save(goods), TimeUtil.getNowTime());
    }

}
