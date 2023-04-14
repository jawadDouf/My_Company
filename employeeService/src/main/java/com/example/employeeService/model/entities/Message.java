package com.example.employeeService.model.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Message {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name ="sender_id",updatable = false,insertable = false)
    private Employee sender;


    @ManyToOne
    @JoinColumn(name="chat_group_id",updatable = false,insertable = false)
    private ChatGroup chatGroup;


    @Column(name = "sender_id")
    private long senderId;


    @Column(name = "chat_group_id")
    private long chatGroupId;

}
