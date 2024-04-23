package com.sp.ecomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class B2cEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2cEcommerceApplication.class, args);
    }
}
