package com.example.employeeService.dto;

import lombok.Data;


@Data
public class AuthResponseDto {

    private String accessToken;
    private String tokenType ="Bearer";

    private long employeeId;

    private String firstName;

    private String lastName;

    private String role;

    public AuthResponseDto(String accessToken, long employeeId, String firstName, String lastName, String role) {
        this.accessToken = accessToken;

        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
