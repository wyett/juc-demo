package com.wyett.order.controller;

import com.wyett.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author : wyettLei
 * @date : Created in 2020/2/29 13:09
 * @description: TODO
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/getOrderInfo")
    @ResponseBody
    public Map<String, Object> getMoneyInfo(@RequestParam(required = false) String orderCode) {
        return orderService.queryOrderInfo(orderCode);
    }


}
