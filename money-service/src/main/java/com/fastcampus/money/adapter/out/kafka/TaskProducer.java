package com.fastcampus.money.adapter.out.kafka;

import com.fastcampus.money.application.port.out.kafka.SendRechargingMoneyTaskPort;
import com.fastcampuspay.common.task.RechargingMoneyTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class TaskProducer implements SendRechargingMoneyTaskPort {
    private final KafkaProducer<String, String> producer;
    private final String topic;

    public TaskProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                        @Value("${task.topic}") String topic) {

        Properties properties = new Properties();
        properties.put("bootstrap.server", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    @Override
    public void sendRechargingMoneyTaskPort(RechargingMoneyTask rechargingMoneyTask) {
        this.sendMessage(rechargingMoneyTask.getTaskId(), rechargingMoneyTask.toString());
    }

    public void sendMessage(String key, String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStringToProduce;

        // jsonString parsing
        try {
            jsonStringToProduce = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProduce);
        producer.send(record, ((recordMetadata, e) -> {
            if (e == null) {
                // send successfully.
            } else {
                // fail to send.
                e.printStackTrace();
            }
        }));
    }
}
