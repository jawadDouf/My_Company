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


    //get all employees of a department
    public List<EmployeeResponse> getAllEmployeesOfDepartment(long idD){
        return employeeRepo.getAllByIdD(idD).stream().map(employeeResponse::to_dto).toList();
    }

    //get all employees of a space
    public List<EmployeeResponse> getAllEmployeesOfSpace(long idS){
        return employeeRepo.getAllByIdS(idS).stream().map(employeeResponse::to_dto).toList();
    }

    //get all employees of a profession
    public List<EmployeeResponse> getAllEmployeesOfProfession(long idP){
        return employeeRepo.getAllByIdP(idP).stream().map(employeeResponse::to_dto).toList();
    }

    //get all employees of a miniDeps
    public List<EmployeeResponse> getAllEmployeesOfMiniDeps(long idMD){
        return employeeRepo.getAllByIdMD(idMD).stream().map(employeeResponse::to_dto).toList();
    }

    //Delete employees
    public void deleteAnEmployee(long id){
         employeeRepo.deleteById(id);
    }


}
