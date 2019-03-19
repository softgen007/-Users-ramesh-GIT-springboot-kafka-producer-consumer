package demo.springboot.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import demo.springboot.kafka.consumer.model.User;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "kafka-message-topic", groupId = "group_message",
    		containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "kafka-json-topic", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Consumed JSON Message: " + user);
    }
}
