package com.example.employeeService.repositories;

import com.example.employeeService.model.entities.ChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatGroupRepo extends JpaRepository<ChatGroup,Long> {


    //Get chat group by idU
    ChatGroup findChatGroupByIdU(Long idU);
}
