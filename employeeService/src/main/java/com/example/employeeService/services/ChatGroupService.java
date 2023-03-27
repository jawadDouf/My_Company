package com.example.employeeService.services;


import com.example.employeeService.dto.ChatGroupDto;
import com.example.employeeService.repositories.ChatGroupRepo;
import org.springframework.stereotype.Service;

@Service
public class ChatGroupService {


    private ChatGroupRepo chatGroupRepo;

    private ChatGroupDto chatGroupDto;

public ChatGroupService(ChatGroupRepo chatGroupRepo, ChatGroupDto chatGroupDto) {
        this.chatGroupRepo = chatGroupRepo;
        this.chatGroupDto = chatGroupDto;
    }


    //Save chat group to database
    public void saveChatGroup(ChatGroupDto chatGroupDto){
        chatGroupRepo.save(chatGroupDto.to_entity());
    }


    //Get chat group from database by idU
    public ChatGroupDto getChatGroup(Long idU){
        return chatGroupDto.to_dto(chatGroupRepo.findChatGroupByIdU(idU));
    }





}
