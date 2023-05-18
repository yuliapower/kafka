package com.example.demoproducer.config;


import java.util.HashMap;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

    public static final String FIRST_TOPIC = "first_topic";
    public static final String SECOND_TOPIC = "second_topic";


    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public NewTopic firstTopic() {
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

    @Bean
    public ProducerFactory<String, String> producerFactory() {

        var config = new HashMap<String, Object>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(
        ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
