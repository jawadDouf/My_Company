package com.example.employeeService.services;


import com.example.employeeService.dto.EmployeeDto;
import com.example.employeeService.exceptions.BadRequestException;
import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.enums.UnitType;
import com.example.employeeService.repositories.EmployeeRepo;
import com.example.employeeService.responses.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepo employeeRepo;

    private EmployeeDto employeeDto;

    private EmployeeResponse employeeResponse;


    public EmployeeService(EmployeeRepo employeeRepo, EmployeeDto employeeDto,EmployeeResponse employeeResponse) {
        this.employeeRepo = employeeRepo;
        this.employeeDto = employeeDto;
        this.employeeResponse = employeeResponse;
    }

    //Create an employee
    public Employee save(EmployeeDto employee){
        //Check if email already exists
        System.out.println(employee.getEmail());

        if(employeeRepo.getEmployeesByEmail(employee.getEmail()).isPresent()){
            System.out.println("EmployeeService.save");
            throw new BadRequestException("email already exists");
        }else {
            System.out.println("EmployeeService.save2");
            return employeeRepo.save(employee.to_entity());
        }
    }


    //Get one employee by id
    public EmployeeResponse getEmployeeById(long id){
        return employeeResponse.to_dto(employeeRepo.findById(id).get());
    }


    //get all employees of unit
    public List<EmployeeResponse> getAllEmployees(UnitType unitType,long unitId){
        System.out.println("EmployeeService.getAllEmployees");
        return employeeRepo.getAllByIdUAndAndUnit(unitId,unitType).stream().map(employeeResponse::to_dto).toList();
    }

    //Delete employees
    public void deleteAnEmployee(long id){
         employeeRepo.deleteById(id);
    }


}
