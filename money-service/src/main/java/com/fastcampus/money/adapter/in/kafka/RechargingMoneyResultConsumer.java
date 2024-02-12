package com.fastcampus.money.adapter.in.kafka;

import com.fastcampuspay.common.CountDownLatchManager;
import com.fastcampuspay.common.producer.LoggingProducer;
import com.fastcampuspay.common.task.RechargingMoneyTask;
import com.fastcampuspay.common.task.SubTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class RechargingMoneyResultConsumer {

    private final KafkaConsumer<String, String> consumer;
    private static LoggingProducer loggingProducer;
    private static CountDownLatchManager countDownLatchManager;

    public RechargingMoneyResultConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootStrapServers,
                                         @Value("${task.result.topic}") String topic) {

        Properties properties = new Properties();
        // kafka:20292
        properties.put("bootstrap.servers", bootStrapServers);

        properties.put("group.id", "my-group");
        // "key:value"
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message: " + record.key() + " / " + record.value());

                        // record: RechargingTask , 모두 ok 결과라고 가정한 topic
                        ObjectMapper mapper = new ObjectMapper();
                        RechargingMoneyTask task;

                        try {
                            task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        List<SubTask> subTaskList = task.getSubTaskList();

                        boolean taskResult = true;
                        for (SubTask subTask : subTaskList) {
                            if (subTask.getStatus().equals("fail")) {
                                taskResult = false;
                                break;
                            }
                        }

                        if (taskResult) {
                            this.loggingProducer.sendMessage(task.getTaskId(), "task success");
                            this.countDownLatchManager.setDataForKey(task.getTaskId(), "success");
                        } else {
                            this.loggingProducer.sendMessage(task.getTaskId(), "task failed");
                            this.countDownLatchManager.setDataForKey(task.getTaskId(), "failed");
                        }

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        this.countDownLatchManager.getCountDownLatch(task.getTaskId());
                    }
                }
            } finally {
                consumer.close();
            }
        });
    }
}
