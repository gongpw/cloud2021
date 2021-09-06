package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.paymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private paymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/payment/hystix/ok/{id}")
    public String payment_Info_ok(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_ok(id);
        log.info("__________result " + result);
        return  result;
    }
                                      //超时要调用的方法   服务降级
    @HystrixCommand(fallbackMethod = "payment_Info_fallBack" , commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    @GetMapping("/payment/hystix/timeout/{id}")
    public String payment_Info_TimeOut(@PathVariable("id") Integer id){
        String s = paymentService.payment_Info_Timeout(id);
        log.info("**************s " + s);
        return s;
    }

    public String payment_Info_fallBack(@PathVariable("id") Integer id){
        return "线程池:  "+Thread.currentThread().getName()+"  8001系统繁忙或者运行报错，请稍后再试,id:  "+id+"\t"+"o(╥﹏╥)o";
    }

    // =====服务熔断==========
    @GetMapping("/payment/circut/get/{id}")
    public String  paymentCircutBreaker(@PathVariable("id") Integer id){
        String s = paymentService.paymentCircuitBreaker(id);
        log.info(s);
        return s;
    }





}

