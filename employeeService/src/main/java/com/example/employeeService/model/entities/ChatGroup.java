package com.example.employeeService.model.entities;


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
public class ChatGroup {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long idU;

    @OneToMany(mappedBy = "chatGroup")
    private List<Message> messages;


    @OneToMany(mappedBy = "id.chatGroup")
    private List<Employee_GroupChat> employee_groupChats;





}
