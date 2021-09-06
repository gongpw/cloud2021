package com.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class paymentService {

    public String paymentInfo_ok(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_ok , id" + id + "\t" + "正常" ;
    }

    public String payment_Info_Timeout (Integer id){
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        }catch (Exception e) {
            e.printStackTrace();
        }

         return "线程池" + Thread.currentThread().getName() +" id:  "+id+"\t"+"O(∩_∩)O哈哈~"+"  耗时(秒): 3";
    }



    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuiFallback" , commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000") ,//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" , value = "60") //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw  new RuntimeException("====id====不能为负数" );
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t"  + "流水号" + uuid;
    }

    public String paymentCircuiFallback(@PathVariable("id") Integer id){
        return "id 不能为负数   " +  id ;
    }


}
