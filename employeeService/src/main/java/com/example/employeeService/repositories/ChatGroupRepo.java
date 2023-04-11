package com.example.employeeService.repositories;


import com.example.employeeService.model.entities.ChatGroup;
import com.example.employeeService.model.enums.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatGroupRepo extends JpaRepository<ChatGroup,Long> {


    //Get chat group by idU
    ChatGroup findChatGroupByIdU(Long idU);

    //Get ChatGroup by id unit and unit type
    ChatGroup getChatGroupByIdUAndAndUnit(Long idU, UnitType unitType);
}
