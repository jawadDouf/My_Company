package com.example.employeeService.controller;


import com.example.employeeService.dto.MessageDto;
import com.example.employeeService.model.entities.Message;
import com.example.employeeService.services.MessageServices;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ChatController {



    private MessageServices messageServices;


    public ChatController(MessageServices messageServices) {

        this.messageServices = messageServices;
    }




    //Recieve ,Store and Send message to the other clients
    @MessageMapping("/sending")
    @SendTo("/topic/public")
    public MessageDto sendMessage(@Payload MessageDto chatMessage) {

        System.out.println(chatMessage.getMessage() + " " + chatMessage.getChatGroupId() + " " + chatMessage.getSenderId());

        //Save message to database
        messageServices.saveMessage(chatMessage);
        //Return message to the other clients
        return chatMessage;
    }



}
