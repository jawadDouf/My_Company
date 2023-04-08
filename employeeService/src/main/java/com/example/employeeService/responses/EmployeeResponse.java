package com.example.employeeService.responses;


import com.example.employeeService.dto.EmployeeDto;
import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.enums.UnitType;
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
public class EmployeeResponse {

    private long id;

    private String first_name;

    private String last_name;

    private String email;

    private String spaceName;

    private long idS;

    private String departementName;

    private long idD;

    private String professionName;

    private long idP;

    private String miniDepsName;

    private long idMD;


    //Convert entity to dto
    public EmployeeResponse to_dto(Employee employee){
        return EmployeeResponse.builder()
                .id(employee.getId())
                .first_name(employee.getFirst_name())
                .last_name(employee.getLast_name())
                .email(employee.getEmail())
                .spaceName(employee.getSpaceName())
                .idS(employee.getIdS())
                .departementName(employee.getDepartementName())
                .idD(employee.getIdD())
                .professionName(employee.getProfessionName())
                .idP(employee.getIdP())
                .miniDepsName(employee.getMiniDepsName())
                .idMD(employee.getIdMD())
                .build();


    }



}
