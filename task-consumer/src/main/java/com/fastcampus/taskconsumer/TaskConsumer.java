package com.fastcampus.taskconsumer;

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
import java.util.Properties;

public class TaskConsumer {

    private final KafkaConsumer<String, String> consumer;
    private static TaskResultProducer taskResultProducer;

    public TaskConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootStrapServers,
                        @Value("${task.topic}") String topic) {

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

                    ObjectMapper mapper = new ObjectMapper();
                    RechargingMoneyTask task;

                    for (ConsumerRecord<String, String> record : records) {
                        // record : RechargingMoneyTask (jsonString)
                        
                        // task run
                        try {
                            task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        for (SubTask subTask : task.getSubTaskList()) {
                            // what subtask, membership, banking
                            // external port, adapter
                            // hexagonal architecture

                            // 모두 정상처리 됐다고 가정
                            subTask.setStatus("success");
                        }

                        // task result 판단
                        // produce TaskResult!! -> 처리 결과를 다시 produce
                        this.taskResultProducer.sendMessage(task.getTaskId(), String.valueOf(task));


                    }
                }
            } finally {
                consumer.close();
            }
        });
    }
}
