package com.fastcampuspay.common;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Configuration
public class CountDownLatchManager {
    private final Map<String, CountDownLatch> countDownLatchManagerMap;
    private final Map<String, String> stringStringMap;

    public CountDownLatchManager() {
        this.countDownLatchManagerMap = new HashMap<>();
        this.stringStringMap = new HashMap<>();

//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        countDownLatch.countDown(); // 다른 쓰레드가 countDown 을 해줘야 아래로 내려감.
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void addCountDownLatch(String key) {
        this.countDownLatchManagerMap.put(key, new CountDownLatch(1));
    }

    public void setDataForKey(String key, String data) {
        this.stringStringMap.put(key, data);
    }

    public CountDownLatch getCountDownLatch(String key) {
        return this.countDownLatchManagerMap.get(key);
    }

    public String getDataForKey(String key) {
        return this.stringStringMap.get(key);
    }
}
