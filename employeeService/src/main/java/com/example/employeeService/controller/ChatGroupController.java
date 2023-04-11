package com.example.employeeService.controller;


import com.example.employeeService.dto.ChatGroupDto;
import com.example.employeeService.dto.MessageDto;
import com.example.employeeService.exceptions.BadRequestException;
import com.example.employeeService.model.enums.UnitType;
import com.example.employeeService.services.ChatGroupService;
import com.example.employeeService.services.MessageServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatgroups")
@CrossOrigin("*")
public class ChatGroupController {


    private ChatGroupService chatGroupService;

    private MessageServices messageServices;


    public ChatGroupController(ChatGroupService chatGroupService,MessageServices messageServices) {
        this.chatGroupService = chatGroupService;
        this.messageServices = messageServices;
    }


    // get messages from a chat group
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getChatGroupMessages(@RequestParam("id") long id, @RequestParam("unitType") UnitType unitType){

        try{

            //Bring the chatgroup
            ChatGroupDto chatGroupDto = chatGroupService.getChatGroupByIduAndUnitType(id,unitType);

            //bring the messages
            List<MessageDto> messageDtos = messageServices.getMessagesOfChatGroup(chatGroupDto.getId());

            // Return the list of messages
            return new ResponseEntity<>(messageDtos, HttpStatus.OK);

        }catch (Exception e){
            throw new BadRequestException(e.getMessage());

        }
    }
}
