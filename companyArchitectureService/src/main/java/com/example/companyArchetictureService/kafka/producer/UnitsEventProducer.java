package com.example.companyArchetictureService.kafka.producer;

import com.example.common.events.TestEvent;
import com.example.common.events.companyUnitsEvents.UnitCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class UnitsEventProducer {


//    private KafkaTemplate<String,UnitCreatedEvent> kafkaTemplate;

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;
    private KafkaTemplate<String, TestEvent> kafkaTemplate;
//    public UnitsEventProducer(KafkaTemplate<String, UnitCreatedEvent> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }


    public UnitsEventProducer(KafkaTemplate<String, TestEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendUnitCreatedEvent(TestEvent testEvent){
        //prepare the event
//        Message<UnitCreatedEvent> message = MessageBuilder
//                .withPayload(unitCreatedEvent)
//                .setHeader(KafkaHeaders.TOPIC,"unitTopic")
//                .build();
        //send event to kafka
        try {
            kafkaTemplate.send(topicJsonName,testEvent);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
