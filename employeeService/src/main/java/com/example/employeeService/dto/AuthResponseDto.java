package com.example.employeeService.dto;

import lombok.Data;


@Data
public class AuthResponseDto {

    private String accessToken;
    private String tokenType ="Bearer";

    private long employeeId;
    public AuthResponseDto(String accessToken,long employeeId){
        this.accessToken=accessToken;
        this.employeeId=employeeId;
    }




}
