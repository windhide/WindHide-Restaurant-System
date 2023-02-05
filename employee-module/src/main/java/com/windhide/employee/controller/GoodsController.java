package com.windhide.employee.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windhide.employee.pojo.Goods;
import com.windhide.employee.pojo.GoodsType;
import com.windhide.employee.service.GoodsService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsTypeService goodsTypeService;

    Map<Integer, GoodsType> goodsTypeMap;


    @RequestMapping("select")
    public T selectAllGoods(@RequestBody HashMap<String,Integer> hashMap){
        Map<String,Object> dataMap = new HashMap<>();
        //分页
        PageInfo info = new PageInfo<>(PageHelper.startPage(hashMap.get("pageNum"),hashMap.get("pageSize")).getResult());
        goodsTypeInit();
        List<Goods> goodsList = goodsService.list();
        goodsList.replaceAll(this::goodsTypeInit);
        dataMap.put("data",goodsList);
        dataMap.put("pageInfo",info);
        return new T(StateCode.SUCCESS, dataMap, TimeUtil.getNowTime());
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

    /**
     * 加载菜单类型
     */
    public void goodsTypeInit() {
        goodsTypeMap = goodsTypeService.list().stream().collect(Collectors.toMap(GoodsType::getGoodsTypeId, goodsType -> goodsType));
    }

    /**
     * 植入菜单类型
     *
     * @param goods
     * @return
     */
    public Goods goodsTypeInit(Goods goods) {
        GoodsType tempGoodsType = goodsTypeMap.get(goods.getGoodsTypeId());
        goods.setGoodsType(tempGoodsType);
        return goods;
    }
}
