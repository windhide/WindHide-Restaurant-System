package com.windhide.abonement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.windhide.abonement.mapper")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AbonementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AbonementApplication.class, args);
    }
}
