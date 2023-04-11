package com.example.employeeService.repositories;

import com.example.employeeService.model.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message,Long> {



    //Bring all the messages of a chat group
    List<Message> findAllByChatGroupId(long chatGroupId);




}
