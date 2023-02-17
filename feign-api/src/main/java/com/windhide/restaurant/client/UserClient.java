package com.windhide.restaurant.client;

import com.windhide.restaurant.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "abonement-module")
public interface UserClient {
    @RequestMapping("user/userLogin")
    User queryUserByLogin(@RequestBody User user);
}
