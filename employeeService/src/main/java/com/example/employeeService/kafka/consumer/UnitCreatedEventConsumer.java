package com.example.employeeService.kafka.consumer;

import com.example.common.events.companyUnitsEvents.UnitCreatedEvent;
import com.example.employeeService.dto.ChatGroupDto;
import com.example.employeeService.services.ChatGroupService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public class UnitCreatedEventConsumer {
    private ChatGroupService chatGroupService;

    private ChatGroupDto chatGroupDto;

    public UnitCreatedEventConsumer(ChatGroupService chatGroupService, ChatGroupDto chatGroupDto) {
        this.chatGroupService = chatGroupService;
        this.chatGroupDto = chatGroupDto;
    }


    // Receive Event from Kafka Topic
    @KafkaListener(topics = "unitTopic", groupId = "employeeService-consumer-group-id")
    public void consume(UnitCreatedEvent unitCreatedEvent) {

        System.out.println("Consumed message: " + unitCreatedEvent.getName());
        //Prepare the chat group dto
        chatGroupDto.setIdU(unitCreatedEvent.getUnitId());
        chatGroupDto.setName(unitCreatedEvent.getName());
        chatGroupDto.setDescription(unitCreatedEvent.getDescription());
        chatGroupService.saveChatGroup(chatGroupDto);
    }
}
