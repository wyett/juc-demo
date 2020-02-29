package com.wyett.order.service;

import com.wyett.order.remoteService.RemoteServiceCall;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author : wyettLei
 * @date : Created in 2020/2/29 13:09
 * @description: TODO
 */

public class OrderService {
    @Autowired
    RemoteServiceCall remoteServiceCall;

    public Map<String, Object> queryOrderInfo(String orderCode) {
        //调用远程接口
        return remoteServiceCall.queryOrderInfoByCode(orderCode);
    }
}
