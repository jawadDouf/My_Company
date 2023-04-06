package com.example.employeeService.controller;


import com.example.employeeService.dto.MessageDto;
import com.example.employeeService.model.entities.Message;
import com.example.employeeService.services.MessageServices;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ChatController {



    private MessageServices messageServices;

    private SimpMessagingTemplate simpMessagingTemplate;


    public ChatController(MessageServices messageServices, SimpMessagingTemplate simpMessagingTemplate) {

        this.messageServices = messageServices;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    //Recieve ,Store and Send message to the other clients
    @MessageMapping("/sending/{chatGroupId}")
    public void sendMessage(@Payload MessageDto chatMessage, @DestinationVariable String chatGroupId) {

        System.out.println(chatMessage.getMessage() + " " + chatMessage.getChatGroupId() + " " + chatMessage.getSenderId());
        System.out.println(chatGroupId);
        //Save message to database
        messageServices.saveMessage(chatMessage);
        //Return message to the other clients
        simpMessagingTemplate.convertAndSend("/topic/public/" + chatGroupId,chatMessage);
    }



}
