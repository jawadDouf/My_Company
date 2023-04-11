package com.example.employeeService.security;


import com.example.employeeService.model.entities.Employee;
import com.example.employeeService.model.entities.Roles;
import com.example.employeeService.repositories.EmployeeRepo;
import com.example.employeeService.repositories.RoleRepo;
import com.example.employeeService.services.EmployeeService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.stream.Collectors;
@Service
public class CustomeUserDetailsService implements UserDetailsService {

    private EmployeeRepo employeeRepo;

    private EmployeeService employeeService;
    private RoleRepo roleRepository;
//
//
    public CustomeUserDetailsService(EmployeeRepo employeeRepo, RoleRepo roleRepository,EmployeeService employeeService) {
        this.employeeRepo = employeeRepo;
        this.roleRepository = roleRepository;
        this.employeeService = employeeService;
    }



     // Get the employee with the credential
     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Employee employee = employeeService.getEmployeeByEmail(email);
            return new User(employee.getEmail(),employee.getPassword(), Collections.singleton(mapRolesToAuthorities(employee.getRole())));
    }



    private GrantedAuthority mapRolesToAuthorities(Roles role){
        return new SimpleGrantedAuthority(role.getRole().name());
    }
}
