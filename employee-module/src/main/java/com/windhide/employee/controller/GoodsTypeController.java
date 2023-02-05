package com.windhide.employee.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windhide.employee.pojo.GoodsType;
import com.windhide.employee.service.GoodsTypeService;
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
@RequestMapping("goodsType")
public class GoodsTypeController {

    @Autowired
    GoodsTypeService goodsTypeService;

    @RequestMapping("select")
    public T selectAllGoodsType(@RequestBody HashMap<String,Integer> hashMap){
        Map<String,Object> dataMap = new HashMap<>();
        //分页
        PageInfo info = new PageInfo<>(PageHelper.startPage(hashMap.get("pageNum"),hashMap.get("pageSize")).getResult());
        dataMap.put("data",goodsTypeService.list());
        dataMap.put("pageInfo",info);
        return new T(StateCode.SUCCESS,dataMap, TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateGoodsTypeById(@RequestBody GoodsType goodsType){
        return new T(StateCode.SUCCESS,goodsTypeService.updateById(goodsType),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{goodsTypeId}")
    public T removeGoodsTypeById(@PathVariable("goodsTypeId") int goodsTypeId){
        return new T(StateCode.SUCCESS,goodsTypeService.removeById(goodsTypeId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody GoodsType goodsType) {
        return new T(StateCode.SUCCESS,goodsTypeService.save(goodsType), TimeUtil.getNowTime());
    }

}
