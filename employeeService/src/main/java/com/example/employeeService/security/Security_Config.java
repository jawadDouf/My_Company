package com.example.employeeService.security;


//import com.example.employeeService.model.enums.RoleType;
import com.example.employeeService.model.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class Security_Config {

    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final CustomeUserDetailsService userDetailsService;


    @Autowired
    public Security_Config(JwtAuthEntryPoint jwtAuthEntryPoint, CustomeUserDetailsService userDetailsService) {
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        System.out.println("Security_Config.securityFilterChain");
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/authentification/**")
                                .permitAll()
                                .requestMatchers("/chatApplication/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/employees/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/chatgroups/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/**").hasRole(RoleType.ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/api/**").hasRole(RoleType.UNITADMIN.name())
                                .anyRequest().authenticated()
//                                .anyRequest().authenticated()
                                //.requestMatchers(HttpMethod.POST,"/api/employees/**").
                );
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }




}
