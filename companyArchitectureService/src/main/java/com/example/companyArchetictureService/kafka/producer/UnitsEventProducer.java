package com.example.companyArchetictureService.kafka.producer;

import com.example.common.events.companyUnitsEvents.UnitCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class UnitsEventProducer {


    private KafkaTemplate<String,UnitCreatedEvent> kafkaTemplate;

    public UnitsEventProducer(KafkaTemplate<String, UnitCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUnitCreatedEvent(UnitCreatedEvent unitCreatedEvent){
        //prepare the event
//        Message<UnitCreatedEvent> message = MessageBuilder
//                .withPayload(unitCreatedEvent)
//                .setHeader(KafkaHeaders.TOPIC,"unitTopic")
//                .build();
        //send event to kafka
        kafkaTemplate.send("unitTopic",unitCreatedEvent);
    }
}
