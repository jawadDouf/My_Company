package com.example.employeeService.dto;


import com.example.employeeService.model.embeddedClasses.EmployeeGrChatId;
import com.example.employeeService.model.entities.ChatGroup;
import com.example.employeeService.model.entities.Employee_GroupChat;
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
public class ChatGroupEmployeesDto {

    private ChatGroupDto chatGroupDto;

    private EmployeeDto employeeDto;




    //Convert entity to dto

    public ChatGroupEmployeesDto to_dto(Employee_GroupChat employee_groupChat) {
        return ChatGroupEmployeesDto.builder()
                .employeeDto(new EmployeeDto().to_dto(employee_groupChat.getId().getEmployee()))
                .build();
    }













    }
