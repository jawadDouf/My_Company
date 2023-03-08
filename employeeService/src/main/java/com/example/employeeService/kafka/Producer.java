package com.example.employeeService.kafka;


import com.example.common.events.TestEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {


    private KafkaTemplate<String, TestEvent> kafkaTemplate;

    public Producer(KafkaTemplate<String, TestEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    // Send Object to Kafka Topic
    public TestEvent produce(TestEvent testEvent) {
        kafkaTemplate.send("testTopic", testEvent);
        return testEvent;
    }
}
