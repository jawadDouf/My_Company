package com.example.employeeService.model.entities;


import com.example.employeeService.model.embeddedClasses.EmployeeGrChatId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_group_chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee_GroupChat {


    @EmbeddedId
    private EmployeeGrChatId id;

}
