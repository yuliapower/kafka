package com.example.demoproducer.service;

import com.example.demoproducer.config.KafkaProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {

        kafkaTemplate.send(KafkaProducerConfig.FIRST_TOPIC, message);

        kafkaTemplate.send(KafkaProducerConfig.SECOND_TOPIC, message);

       /*
       ListenableFuture<SendResult<String, String>> future = kafkaTemplate
       .send(KafkaProducerConfig.FIRST_TOPIC, message);

       future.addCallback(new ListenableFutureCallback<>() {
             @Override
             public void onSuccess(SendResult<String, String> result) {
                 log.info("Successful send message to Kafka, topic {} with offset {} to partition {}",
                     result.getProducerRecord().topic(),
                     result.getRecordMetadata().offset(),
                     result.getRecordMetadata().partition());
             }

             @Override
             public void onFailure(Throwable ex) {
                 log.error("Unable to send message = {} dut to: {}", message, ex.getMessage());
             }
      });
   */
    }

}
