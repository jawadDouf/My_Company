package com.example.employeeService.dto;


import com.example.employeeService.model.entities.ChatGroup;
import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.entities.Employee_GroupChat;
import com.example.employeeService.model.entities.Message;
import com.example.employeeService.model.enums.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("singleton")
public class ChatGroupDto {


    private Long id;

    private String name;

    private String description;

    private Long idU;

    private UnitType unit;

    private List<MessageDto> messages;

    private List<EmployeeDto> employees;

    //Convert dto to entity
    public ChatGroup to_entity(){
        return ChatGroup.builder()
                .name(this.getName())
                .description(this.getDescription())
                .idU(this.getIdU())
                .unit(this.getUnit())
                .build();
    }

    //Convert entity to dto
    public ChatGroupDto to_dto(ChatGroup chatGroup){
        return ChatGroupDto.builder()
                .id(chatGroup.getId())
                .name(chatGroup.getName())
                .description(chatGroup.getDescription())
                .idU(chatGroup.getIdU())
                .unit(chatGroup.getUnit())
                .messages(chatGroup.getMessages().stream().map(new MessageDto()::to_dto).toList())
                .employees(chatGroup.getEmployee_groupChats().stream().map(employeeGroupChat -> new ChatGroupEmployeesDto().to_dto(employeeGroupChat).getEmployeeDto()).toList())
                .build();
    }

}
