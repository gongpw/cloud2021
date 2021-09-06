package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix   //在客户端使用服务降级
public class FeignOrder80Main {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrder80Main.class,args);
    }
}
