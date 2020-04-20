package com.wyett.order.service;

import com.wyett.order.remoteService.RemoteServiceCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author : wyettLei
 * @date : Created in 2020/2/29 15:21
 * @description: TODO
 */

@Service
public class MyOrderService {

    class Request {
        String orderCode;
        String serialNo;
        CompletableFuture<Map<String, Object>> future;
    }

    @Autowired
    RemoteServiceCall remoteServiceCall;

    LinkedBlockingDeque<Request> queue = new LinkedBlockingDeque<>();

    public Map<String, Object> queryOrderInfo(String orderCode) throws ExecutionException, InterruptedException {
        String serialNo = UUID.randomUUID().toString();

        Request request = new Request();
        request.serialNo = serialNo;
        request.orderCode = orderCode;

        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        request.future = future;

        queue.add(request);

        //return remoteServiceCall.queryOrderInfoByCode(orderCode);
        // 调用远程接口
        return future.get();
    }

    @PostConstruct
    public void init() {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

        schedule.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // todo
                int size = queue.size();
                if(size == 0) {
                    return;
                }

                ArrayList<Request> requests = new ArrayList<>();
                for(int i = 0; i < size; i++) {
                    Request request = queue.poll();
                    requests.add(request);
                }

                System.out.println("批量处理的数据量为" + size);

                //拼装批量查询参数格式
                List<Map<String, String>> params = new ArrayList<>();
                for(Request request : requests) {
                    Map<String, String> map = new HashMap<>();
                    map.put("orderCode", request.orderCode);
                    map.put("serialNo", request.serialNo);
                    params.add(map);
                }

                List<Map<String, Object>> responses = remoteServiceCall.queryOrderInfoByCodeBatch(params);
                HashMap<String, Map<String, Object>> responseMap = new HashMap<>();

                for(Map<String, Object> response: responses) {
                    String serialNo = response.get("serialNo").toString();
                    responseMap.put(serialNo, response);
                }

                for(Request request : requests) {
                    Map<String, Object> result = responseMap.get(request.serialNo);
                    request.future.complete(result);
                }
            }
        }, 0, 10, TimeUnit.MILLISECONDS);

    }
}
