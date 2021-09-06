package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystixDashboardMain9001.class,args);
    }
}
