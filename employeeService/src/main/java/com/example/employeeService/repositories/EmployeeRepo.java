package com.example.employeeService.repositories;

import com.example.employeeService.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {



    //get all employees of a department
    List<Employee> getAllByIdD(long idD);

    //get all employees of a space
    List<Employee> getAllByIdS(long idS);

    //get all employees of a profession
    List<Employee> getAllByIdP(long idP);

    //get all employees of a miniDeps
    List<Employee> getAllByIdMD(long idMD);



    Boolean existsByEmail(String email);
    //Get Employees By Email

    Optional<Employee> getEmployeesByEmail(String email);


}
