package com.example.companyArchetictureService.kafka;

import com.example.common.events.TestEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {




    //Receive Object from Kafka Topic
    @KafkaListener(topics = "testTopic", groupId = "test-consumer-group-id")
    public void consume(TestEvent testEvent) {

        System.out.println("Consumed message: " + testEvent.getMessage());
        testEvent.setStatus("Consumed");
    }

}
