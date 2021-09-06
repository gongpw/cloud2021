package com.springcloud.service;

import com.springcloud.entity.CommonResult;
import com.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component            // 服务的微服务名称
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")  // 客户端服务接口
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
    @GetMapping("/payment/sleep")
    public String sleep();
}
