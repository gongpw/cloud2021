package com.springcloud.controller;

import com.springcloud.entity.CommonResult;
import com.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/feign/payment/get/{id}")
    public CommonResult get(@PathVariable("id") long id){
       return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/feign/payment/sleep")
    public String sleep(){
        return paymentFeignService.sleep();
    }
}
