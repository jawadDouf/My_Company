package com.example.employeeService.dto;


import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.enums.UnitType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class EmployeeDto {



    private long id;

    private String first_name;

    private String last_name;

    private String email;

    private String password;

    private UnitType unit;

    private long idU;





    //Convert dto to entity
    public Employee to_entity(){
        return Employee.builder()
                .first_name(this.getFirst_name())
                .last_name(this.getLast_name())
                .email(this.getEmail())
                .unit(this.getUnit())
                .password(this.getPassword())
                .idU(this.idU)
                .build();
    }

}
