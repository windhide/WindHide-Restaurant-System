package com.windhide.employee.controller;

import com.github.pagehelper.PageHelper;
import com.windhide.employee.pojo.Dictionaries;
import com.windhide.employee.service.impl.DictionariesServiceImpl;
import com.windhide.restaurant.pojo.T;
import com.windhide.restaurant.util.StateCode;
import com.windhide.restaurant.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aictionaries")
public class DictionariesController {

    @Autowired
    DictionariesServiceImpl aictionariesService;

    @RequestMapping("select")
    public T selectAllDictionaries(@RequestBody int pageNum, @RequestBody int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new T(StateCode.SUCCESS,aictionariesService.list(), TimeUtil.getNowTime());
    }

    @RequestMapping("update")
    public T updateDictionariesById(@RequestBody Dictionaries aictionaries){
        return new T(StateCode.SUCCESS,aictionariesService.updateById(aictionaries),TimeUtil.getNowTime());
    }

    @RequestMapping("remove/{aictionariesId}")
    public T removeDictionariesById(@PathVariable("aictionariesId") int aictionariesId){
        return new T(StateCode.SUCCESS,aictionariesService.removeById(aictionariesId),TimeUtil.getNowTime());
    }

    @RequestMapping("insert")
    public T insertOrder(@RequestBody Dictionaries aictionaries) {
        return new T(StateCode.SUCCESS,aictionariesService.save(aictionaries), TimeUtil.getNowTime());
    }

}
