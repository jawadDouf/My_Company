package com.example.employeeService.model.entities;


import com.example.employeeService.model.enums.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String first_name;


    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private String spaceName;

    @Column(nullable = false)
    private long idS;

    @Column(nullable = false)
    private String departementName;

    @Column(nullable = false)
    private long idD;

    @Column(nullable = false)
    private String professionName;

    @Column(nullable = false)
    private long idP;

    @Column(nullable = false)
    private String miniDepsName;

    @Column(nullable = false)
    private long idMD;


    @OneToMany(mappedBy = "sender")
    private List<Message> messages;



    @OneToMany(mappedBy = "id.employee")
    private List<Employee_GroupChat> employee_groupChats;







}
