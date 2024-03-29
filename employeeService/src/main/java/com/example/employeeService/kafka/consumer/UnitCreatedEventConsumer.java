package com.example.employeeService.kafka.consumer;

import com.example.common.events.TestEvent;
import com.example.common.events.companyUnitsEvents.UnitCreatedEvent;
import com.example.employeeService.dto.ChatGroupDto;
import com.example.employeeService.model.enums.UnitType;
import com.example.employeeService.services.ChatGroupService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class UnitCreatedEventConsumer {
    private ChatGroupService chatGroupService;

    private ChatGroupDto chatGroupDto;


    public UnitCreatedEventConsumer(ChatGroupService chatGroupService, ChatGroupDto chatGroupDto) {
        this.chatGroupService = chatGroupService;
        this.chatGroupDto = chatGroupDto;
    }


    // Receive Event from Kafka Topic
    @KafkaListener(topics = "unitTopic")
    public void consume(UnitCreatedEvent unitCreatedEvent) {

        System.out.println("Consumed message: " + unitCreatedEvent.getName());
        //Prepare the chat group dto
        chatGroupDto.setIdU(unitCreatedEvent.getUnitId());
        chatGroupDto.setName(unitCreatedEvent.getName());
        chatGroupDto.setDescription(unitCreatedEvent.getDescription());
        chatGroupDto.setUnit(UnitType.valueOf(unitCreatedEvent.getUnit().name()));
        chatGroupService.saveChatGroup(chatGroupDto);
    }

//    @KafkaListener(topics="${spring.kafka.topic-json.name}",groupId = "${spring.kafka.consumer.group-id}")
//    public void consume(TestEvent testEvent) {
//        System.out.println("Consumed message: " + testEvent.getMessage());
//
//    }
}
