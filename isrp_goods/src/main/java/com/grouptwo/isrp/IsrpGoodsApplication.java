package com.grouptwo.isrp;

import com.grouptwo.isrp.config.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
public class IsrpGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsrpGoodsApplication.class, args);
    }

}
