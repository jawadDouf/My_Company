package com.example.employeeService.controller;


import com.example.common.events.TestEvent;
import com.example.employeeService.dto.EmployeeDto;
import com.example.employeeService.exceptions.BadRequestException;
import com.example.employeeService.helpers.ResponseHandler;
import com.example.employeeService.kafka.Producer;
import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.enums.UnitType;
import com.example.employeeService.repositories.EmployeeRepo;
import com.example.employeeService.responses.EmployeeResponse;
import com.example.employeeService.services.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.example.employeeService.model.enums.UnitType.SPACE;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins="*")
public class EmployeeController {


    private EmployeeService employeeService;

    private ResponseHandler responseHandler;

    private Producer producer;


    public EmployeeController(EmployeeService employeeService,ResponseHandler responseHandler,Producer producer) {
        System.out.println("EmployeeController");
        this.employeeService = employeeService;
        this.responseHandler = responseHandler;
        this.producer = producer;

    }




    //Add an employee
    @PostMapping("/register")
    public ResponseEntity<Object> saveEmployee(@RequestBody  EmployeeDto employeeDto){

        try {
            //Save the employee
            employeeService.save(employeeDto);
            return responseHandler.generateResponse("Employee is created succefully",HttpStatus.OK);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
////        if(personRepo.existsByEmail(registerDto.getEmail())){
////            return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
////        }
//
//        Employee employee = new Employee();
//        employee.setEmail(registerDto.getEmail().toLowerCase());
//        employee.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        person.setLast_name(registerDto.getLast_name());
//        person.setFirst_name(registerDto.getFirst_name());
//        person.setPhone_number(registerDto.getPhone_number());
//        person.setAdresse(registerDto.getAdresse());
//        Roles role = roleRepo.findByName("User").get();
//        person.setRoles(Collections.singletonList(role));
//        personRepo.save(person);
//        return new ResponseEntity<>("User registred success",HttpStatus.OK);
//    }

     //Get all employees of a unit
    @GetMapping()
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(@RequestParam("id") long id,@RequestParam("unitType") UnitType unitType){

        System.out.println("EmployeeController.getAllEmployees");
        //Get all employees of a unit
        List<EmployeeResponse> employees;

        switch (unitType){
            case SPACE:
                    //Get all employees of a unit
                     employees = employeeService.getAllEmployeesOfSpace(id);
                    //Return the response entity
                    return new ResponseEntity<>(employees,HttpStatus.OK);
            case DEPARTEMENT:
                //Get all employees of a unit
                employees = employeeService.getAllEmployeesOfDepartment(id);
                //Return the response entity
                return new ResponseEntity<>(employees,HttpStatus.OK);
            case PROFESSION:
                //Get all employees of a unit
                employees = employeeService.getAllEmployeesOfProfession(id);
                //Return the response entity
                return new ResponseEntity<>(employees,HttpStatus.OK);
            case MINIDEPS:
                //Get all employees of a unit
                employees = employeeService.getAllEmployeesOfMiniDeps(id);
                //Return the response entity
                return new ResponseEntity<>(employees,HttpStatus.OK);
            default:
                throw new BadRequestException("There is no " + unitType.name() + " in the database with this id");
        }

    }


    //Delete an item
    @DeleteMapping ("/{id}")
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


    //Get an Employee by email
    @GetMapping("/{email}")
    public ResponseEntity<Employee> getOneEmployeeByEmail(@PathVariable String email){
        try {
            //Get the employee and Return the response
            return new ResponseEntity<>(employeeService.getEmployeeByEmail(email),HttpStatus.OK);
        }catch (Exception e){
            //Throw an exception
            throw new BadRequestException("There is no employee in the database with this email");
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<String> getOneEmployeeInfoByEmail(@PathVariable String email){
        try {
            //Get the employee
            EmployeeDto emp = new EmployeeDto().to_dto(employeeService.getEmployeeByEmail(email));
            System.out.println(emp.getEmail()+"!#!"+emp.getRolesDto().toString());
            //Return the response
            return new ResponseEntity<>(emp.getEmail()+"!#!"+emp.getRolesDto().getRole().name(),HttpStatus.OK);
        }catch (Exception e){
            //Throw an exception
            throw new BadRequestException("There is no employee in the database with this email");
        }
    }


    //Get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getOneEmployeeByEmail(@PathVariable long id){
        try {
            //Get the employee and Return the response
            return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
        }catch (Exception e){
            //Throw an exception
            throw new BadRequestException("There is no employee in the database with this id");
        }
    }

    //Test Kafka
//    @GetMapping("/test")
//    public ResponseEntity<Object> testKafka(){
//        try {
//            TestEvent testEvent = null;
////            testEvent= producer.produce(new TestEvent());
////            System.out.println("Kafka is working"+testEvent.getStatus());
//            return responseHandler.generateResponse("Kafka is working",HttpStatus.OK);
//        }catch (Exception e){
//            throw new BadRequestException("Kafka is not working");
//        }
//    }
}
