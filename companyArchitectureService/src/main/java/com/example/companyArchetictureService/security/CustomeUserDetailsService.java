package com.example.companyArchetictureService.security;




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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Collectors;
@Service
public class CustomeUserDetailsService implements UserDetailsService {



     private WebClient webClient;


    public CustomeUserDetailsService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Get the employee with the credential
     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            String returnedResult = webClient.get()
                    .uri("http://localhost:8083/api/employees/email/"+email)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return new User(email,returnedResult.split("!#!")[0],Collections.singleton(mapRolesToAuthorities(returnedResult.split("!#!")[1])));
    }

    private GrantedAuthority mapRolesToAuthorities(String role){
        return new SimpleGrantedAuthority(role);
    }
}
