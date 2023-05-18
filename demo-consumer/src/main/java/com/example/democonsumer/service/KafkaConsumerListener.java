package com.example.democonsumer.service;

import com.example.democonsumer.config.KafkaTopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerListener {

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaTopicConfig.FIRST_TOPIC,
        partitionOffsets = {
            @PartitionOffset(partition = "4",initialOffset = "0"),
            @PartitionOffset(partition = "3",initialOffset = "0"),
            @PartitionOffset(partition = "2",initialOffset = "0"),
            @PartitionOffset(partition = "1",initialOffset = "0")
        }), groupId = "test-group" )
    public void firstListener(@Payload String message,
        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {

        log.info("Received message to first listener: {}, from partition:{}", message,partition);
    }

    @KafkaListener(topics = KafkaTopicConfig.SECOND_TOPIC,
        groupId = "test-group")
    public void secondListener(@Payload String message,
        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {

        log.info("Received message to second listener: {}, from partition:{}", message,partition);
    }
}

