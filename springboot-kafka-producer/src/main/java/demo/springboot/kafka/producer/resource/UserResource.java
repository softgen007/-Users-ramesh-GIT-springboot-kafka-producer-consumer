package demo.springboot.kafka.producer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.springboot.kafka.producer.model.User;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;

    private static final String USERTOPIC = "kafka-json-topic";
    private static final String MESSAGETOPIC = "kafka-message-topic";

    @PostMapping("/publish/json")
    public String postJSON(@RequestBody User user) {
    	
        userKafkaTemplate.send(USERTOPIC, user);

        return "Published User successfully";
    }
    
    @GetMapping("/publish/{message}")
    public String postMessage(@PathVariable String message) {
    	
        kafkaTemplate.send(MESSAGETOPIC, message);

        return "Published Message successfully";
    }
}
