package com.example.employeeService.model.entities;


import com.example.employeeService.model.enums.UnitType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "chat_groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ChatGroup {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @Column(nullable = false)
    private Long idU;

    @OneToMany(mappedBy = "chatGroup")
    private List<Message> messages;


    @OneToMany(mappedBy = "id.chatGroup")
    private List<Employee_GroupChat> employee_groupChats;





}
