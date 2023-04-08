package com.windhide.abonement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.windhide.abonement.pojo.Desk;
import com.windhide.abonement.service.DeskService;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("desk")
public class DeskController {

    @Autowired
    DeskService DeskService;

    @RequestMapping("select")
    public T selectAllDesk() {
        return new T(StateCode.SUCCESS, DeskService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("select/{deskId}")
    public T selectAllDesk(@PathVariable("deskId") int deskId) {
        QueryWrapper<Desk> queryWrapper = new QueryWrapper();
        queryWrapper.eq("desk_id", deskId);
        return new T(StateCode.SUCCESS, DeskService.list(queryWrapper), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateDeskById(@RequestBody Desk Desk) {
        return new T(StateCode.SUCCESS, DeskService.updateById(Desk), TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{deksId}")
    public T removeDeskById(@PathVariable("deskId") int deskId) {
        return new T(StateCode.SUCCESS, DeskService.removeById(deskId), TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody Desk desk) {
        return new T(StateCode.SUCCESS, DeskService.save(desk), TimeUtil.getNowTime());
    }

}
