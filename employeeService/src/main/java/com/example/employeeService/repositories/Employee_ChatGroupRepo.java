package com.example.employeeService.repositories;

import com.example.employeeService.model.entities.Employee_GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Employee_ChatGroupRepo extends JpaRepository<Employee_GroupChat, Long> {
}
