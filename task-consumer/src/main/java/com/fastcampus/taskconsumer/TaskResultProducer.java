package com.fastcampus.taskconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

public class TaskResultProducer {
    private final KafkaProducer<String, String> producer;
    private final String topic;

    public TaskResultProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                              @Value("${task.result.topic}") String topic) {

        Properties properties = new Properties();
        properties.put("bootstrap.server", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
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
