package com.example.employeeService.services;



import com.example.employeeService.dto.ChatGroupDto;
import com.example.employeeService.model.enums.UnitType;
import com.example.employeeService.repositories.ChatGroupRepo;
import com.example.employeeService.repositories.MessageRepo;
import org.springframework.stereotype.Service;

@Service
public class ChatGroupService {


    private ChatGroupRepo chatGroupRepo;

    private ChatGroupDto chatGroupDto;

    private MessageRepo messageRepo;

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


    //Get chat group by id unit and unit
    public ChatGroupDto getChatGroupByIduAndUnitType(Long idU, UnitType unitType){
        return chatGroupDto.to_dto(chatGroupRepo.getChatGroupByIdUAndAndUnit(idU,unitType));
    }





}
