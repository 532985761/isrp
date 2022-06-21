package com.grouptwo.isrp;

import com.grouptwo.isrp.config.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
public class IsrpOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsrpOrderApplication.class, args);
    }

}
