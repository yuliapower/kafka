package com.example.democonsumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String FIRST_TOPIC = "first_topic";
    public static final String SECOND_TOPIC = "second_topic";

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(FIRST_TOPIC)
            .partitions(10)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic secondTopic() {
        return TopicBuilder.name(SECOND_TOPIC)
            .replicas(1)
            .build();
    }
}
