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

    private String spaceName;

    private long idS;

    private String departementName;

    private long idD;

    private String professionName;

    private long idP;

    private String miniDepsName;

    private long idMD;





    //Convert dto to entity
    public Employee to_entity(){
        return Employee.builder()
                .id(this.id)
                .first_name(this.first_name)
                .last_name(this.last_name)
                .email(this.email)
                .password(this.password)
                .spaceName(this.spaceName)
                .idS(this.idS)
                .departementName(this.departementName)
                .idD(this.idD)
                .professionName(this.professionName)
                .idP(this.idP)
                .miniDepName(this.miniDepsName)
                .idMD(this.idMD)
                .build();
    }


    //Convert entity to dto
    public EmployeeDto to_dto(Employee employee){
        return EmployeeDto.builder()
                .id(employee.getId())
                .first_name(employee.getFirst_name())
                .last_name(employee.getLast_name())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .spaceName(employee.getSpaceName())
                .idS(employee.getIdS())
                .departementName(employee.getDepartementName())
                .idD(employee.getIdD())
                .professionName(employee.getProfessionName())
                .idP(employee.getIdP())
                .miniDepsName(employee.getMiniDepName())
                .idMD(employee.getIdMD())
                .build();
    }

}
