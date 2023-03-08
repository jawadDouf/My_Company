package com.example.employeeService.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private Employee sender;


    @ManyToOne
    private ChatGroup chatGroup;

}
