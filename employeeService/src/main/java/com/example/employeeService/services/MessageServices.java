package com.example.employeeService.services;


import com.example.employeeService.dto.MessageDto;
import com.example.employeeService.repositories.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServices {



    private MessageRepo messageRepo;

    private MessageDto messageDto;



    public MessageServices(MessageRepo messageRepo, MessageDto messageDto) {
        this.messageRepo = messageRepo;
        this.messageDto = messageDto;
    }


    //Save a message
    public void saveMessage(MessageDto messageDto) {
        messageRepo.save(messageDto.to_entity());
    }

    //Get messages of a chat group
    public List<MessageDto> getMessagesOfChatGroup(long chatGroupId) {
        return messageRepo.findAllByChatGroupId(chatGroupId).stream().map(message -> messageDto.to_dto(message)).toList();
    }

}
