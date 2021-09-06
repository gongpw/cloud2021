package com.springcloud.service;

import com.springcloud.service.impl.paymentGlobalMehtod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "cloud-provider-hystix-payment" , fallback = paymentGlobalMehtod.class) // 当所用要调用的服务器宕机了   要处理的方法
public interface OrderService {
    @GetMapping("/payment/hystix/ok/{id}")
    public String payment_Info_ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystix/timeout/{id}")
    public String payment_Info_TimeOut(@PathVariable("id") Integer id);
}
