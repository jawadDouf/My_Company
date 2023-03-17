package com.example.employeeService.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,insertable = false)
    private Employee sender;


    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,insertable = false)
    private ChatGroup chatGroup;


    @Column(name = "sender_id")
    private long senderId;

    @Column(name = "chat_group_id")
    private long chatGroupId;

}
