package com.example.employeeService.dto;


import com.example.employeeService.model.entities.ChatGroup;
import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.entities.Message;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("singleton")
public class MessageDto {


    private Long id;

    private String message;

    private long senderId;

    private long chatGroupId;

    private String fSender;

    private String lSender;


    //Convert dto to entity
    public Message to_entity(){
        return Message.builder()
                .message(this.getMessage())
                .senderId(this.getSenderId())
                .chatGroupId(this.getChatGroupId())
                .build();
    }

    //Convert entity to dto
    public MessageDto to_dto(Message message){
        return MessageDto.builder()
                .id(message.getId())
                .message(message.getMessage())
                .senderId(message.getSenderId())
                .chatGroupId(message.getChatGroupId())
                .fSender(message.getSender().getFirst_name())
                .lSender(message.getSender().getLast_name())
                .build();
    }
}
