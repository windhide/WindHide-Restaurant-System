package com.windhide.abonement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.windhide.abonement.mapper")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AbonementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AbonementApplication.class, args);
    }
}
