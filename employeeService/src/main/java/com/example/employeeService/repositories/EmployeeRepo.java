package com.example.employeeService.repositories;

import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.enums.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {



    //Get Employees by id and unitName
   List<Employee> getAllByIdUAndAndUnit(long unitId,UnitType unit);

   //Get Employees By Email

    Optional<Employee> getEmployeesByEmail(String email);


}
