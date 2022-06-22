package com.grouptwo.isrp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author 张洪志
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class IsrpGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsrpGatewayApplication.class, args);
        disableWarning();
    }
    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }


}
