package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "globalMethod") // 默认的服务降级的处理方法
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/consumer/payment/hystix/ok/{id}")
    public String payment_Info_ok(@PathVariable("id") Integer id){
        return orderService.payment_Info_ok(id);
    }

    //服务降级
  /*  @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod" , commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @HystrixCommand
    @GetMapping("/consumer/payment/hystix/timeout/{id}")
    public String payment_Info_TimeOut(@PathVariable("id") Integer id){
        return orderService.payment_Info_TimeOut(id);
    }

    //善后方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //全局的善后方法
    public String globalMethod(){
        return "global错误，请注意小心";
    }
}
