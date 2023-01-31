package com.windhide.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windhide.employee.pojo.Goods;
import com.windhide.employee.service.GoodsService;
import com.windhide.employee.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author wuhaoye
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2023-01-31 14:58:23
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




