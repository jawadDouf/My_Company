package com.example.employeeService.controller;


import com.example.common.events.TestEvent;
import com.example.employeeService.dto.EmployeeDto;
import com.example.employeeService.exceptions.BadRequestException;
import com.example.employeeService.helpers.ResponseHandler;
import com.example.employeeService.kafka.Producer;
import com.example.employeeService.model.enums.UnitType;
import com.example.employeeService.repositories.EmployeeRepo;
import com.example.employeeService.responses.EmployeeResponse;
import com.example.employeeService.services.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.employeeService.model.enums.UnitType.SPACE;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    private EmployeeService employeeService;

    private ResponseHandler responseHandler;

    private Producer producer;


    public EmployeeController(EmployeeService employeeService,ResponseHandler responseHandler,Producer producer) {
        this.employeeService = employeeService;
        this.responseHandler = responseHandler;
        this.producer = producer;
    }




    //Add an employee
    @PostMapping()
    public ResponseEntity<Object> saveEmployee(@RequestBody  EmployeeDto employeeDto){

        try {
            //Save the employee
            employeeService.save(employeeDto);
            return responseHandler.generateResponse("Employee is created succefully",HttpStatus.OK);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }


    //Get employees of a unit
    @GetMapping("/unit")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesOfUnit(@RequestParam("id") long id, @RequestParam("unit") UnitType unitType){

        try {

            return new ResponseEntity<>(employeeService.getAllEmployees(unitType,id),HttpStatus.OK);
        }catch (Exception e){
            throw new BadRequestException("There is something wrong in your entered data synatx");
        }

    }


    //Delete an item
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable long id){

        try {
              //delete the employee
              employeeService.deleteAnEmployee(id);
              //Return the response
              return responseHandler.generateResponse("employee deleted successfully",HttpStatus.OK);
        }catch (Exception e){
            //Throw an exception
            throw new BadRequestException("There is no employee in the database with this id");
        }
    }

    //Test Kafka
    @GetMapping("/test")
    public ResponseEntity<Object> testKafka(){
        try {
            producer.produce(new TestEvent());
            return responseHandler.generateResponse("Kafka is working",HttpStatus.OK);
        }catch (Exception e){
            throw new BadRequestException("Kafka is not working");
        }
    }
}
