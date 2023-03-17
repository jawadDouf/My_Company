package com.example.employeeService.model.embeddedClasses;


import com.example.employeeService.model.entities.ChatGroup;
import com.example.employeeService.model.entities.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class EmployeeGrChatId implements Serializable {


    @ManyToOne
    private Employee employee;


    @ManyToOne
    private ChatGroup chatGroup;



}
