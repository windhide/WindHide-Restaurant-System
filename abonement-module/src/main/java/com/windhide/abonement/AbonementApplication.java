package com.windhide.abonement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableFeignClients(clients = {StaffClient.class, UserCilent.class})
public class AbonementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AbonementApplication.class, args);
    }
}
