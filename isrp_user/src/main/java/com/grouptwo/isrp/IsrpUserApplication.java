package com.grouptwo.isrp;

import com.grouptwo.isrp.client.TestClient;
import com.grouptwo.isrp.config.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author 张洪志
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class IsrpUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsrpUserApplication.class, args);
    }

}
