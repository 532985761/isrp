package com.grouptwo.isrp;

import com.grouptwo.isrp.config.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
@SpringBootApplication
public class IsrpUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsrpUserApplication.class, args);
    }

}
