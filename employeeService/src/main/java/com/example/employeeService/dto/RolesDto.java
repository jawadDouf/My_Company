package com.example.employeeService.dto;


import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.entities.Roles;
import com.example.employeeService.model.enums.RoleType;
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
public class RolesDto {

    private long id;

    private RoleType role;


    //Convert dto to entity
    public Roles to_entity(){
        return Roles.builder()
                .id(this.id)
                .role(this.getRole())
                .build();
    }

    //Convert to dto
    public RolesDto to_dto(Roles role){
        return RolesDto.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();
    }

}
