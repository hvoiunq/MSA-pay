package com.fastcampuspay.common.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

public class LoggingProducer {

    private final KafkaProducer<String, String> producer;
    private final String topic;

    public LoggingProducer(@Value("${kafka.clusters.bootstrapservers}") String bootStrapServers,
                           @Value("${logging.topic}") String topic) {

        Properties properties = new Properties();
        // kafka:20292
        properties.put("bootstrap.servers", bootStrapServers);

        // "key:value"
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    // producer send
    public void sendMessage(String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
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
