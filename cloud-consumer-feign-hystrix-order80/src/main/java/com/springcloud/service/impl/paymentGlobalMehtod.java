package com.springcloud.service.impl;

import com.springcloud.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class paymentGlobalMehtod implements OrderService {
    @Override
    public String payment_Info_ok(Integer id) {
        return "payment_Info_ok 服务器宕机了 ！！！！！！";
    }

    @Override
    public String payment_Info_TimeOut(Integer id) {
        return "payment_Info_TimeOut  服务器宕机了！！！！！！";
    }
}
