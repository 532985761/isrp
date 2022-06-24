package com.grouptwo.isrp;

import com.grouptwo.isrp.config.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 张洪志
 */
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class, basePackages = {"com.grouptwo.isrp"})
public class IsrpOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsrpOrderApplication.class, args);
    }

}
