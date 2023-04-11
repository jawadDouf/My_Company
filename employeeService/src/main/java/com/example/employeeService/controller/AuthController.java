package com.example.employeeService.controller;



import com.example.employeeService.model.entities.*;
import com.example.employeeService.model.enums.RoleType;
import com.example.employeeService.repositories.EmployeeRepo;
import com.example.employeeService.repositories.RoleRepo;
import com.example.employeeService.security.JWTGenerator;
import com.example.employeeService.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.employeeService.dto.*;


import java.util.Collections;

@RestController
@RequestMapping("api/authentification")
@CrossOrigin(origins="*")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private EmployeeRepo employeeRepo;
    private RoleRepo roleRepo;

    private EmployeeService employeeService;
    private  PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;


    public AuthController(AuthenticationManager authenticationManager, EmployeeRepo employeeRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator,EmployeeService employeeService) {
        this.authenticationManager = authenticationManager;
        this.employeeRepo = employeeRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token,employeeService.getEmployeeByEmail(loginDto.getEmail()).getId()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody EmployeeDto employeeDto){
        if(employeeRepo.existsByEmail(employeeDto.getEmail())){
            return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
        }

        Employee employee = new Employee();
        employee.setEmail(employeeDto.getEmail().toLowerCase());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employee.setLast_name(employeeDto.getLast_name());
        employee.setFirst_name(employeeDto.getFirst_name());
        employee.setDepartementName(employeeDto.getDepartementName());
        employee.setIdD(employeeDto.getIdD());
        employee.setIdMD(employeeDto.getIdMD());
        employee.setIdP(employeeDto.getIdP());
        employee.setIdS(employeeDto.getIdS());
        employee.setMiniDepName(employeeDto.getMiniDepName());
        employee.setProfessionName(employeeDto.getProfessionName());
        employee.setSpaceName(employeeDto.getSpaceName());
        Roles role = roleRepo.findByRole(RoleType.ADMIN).get();
        employee.setRole(role);
        employeeRepo.save(employee);
        return new ResponseEntity<>("User registred success",HttpStatus.OK);
    }
}
