package com.wyett.order.remoteService;

import java.util.*;

/**
 * @author : wyettLei
 * @date : Created in 2020/2/29 13:10
 * @description: TODO
 */

public class RemoteServiceCall {

    public Map<String, Object> queryOrderInfoByCode(String orderCode) {

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("orderMoney", new Random().nextInt(9999));
        hashMap.put("orderCode", orderCode);
        //hashMap.put("serialNo", o.getSerialNo());
        hashMap.put("orderTime", "20200229");
        hashMap.put("orderStatus", "finish");

        return hashMap;
    }

    public List<Map<String, Object>> queryOrderInfoByCodeBatch(List<Map<String, String>> params) {
        List<Map<String, Object>> result = new ArrayList<>();

        for(Map<String, String> param: params) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("orderMoney", new Random().nextInt(9999));
            hashMap.put("orderCode", param.get("orderCode"));
            hashMap.put("serialNo", param.get("serialNo"));
            hashMap.put("orderTime", "20200229");
            hashMap.put("orderStatus", "finish");
            result.add(hashMap);
        }

        return result;
    }


}
