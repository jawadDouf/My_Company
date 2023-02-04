package com.example.companyArchetictureService.requests;

import com.example.companyArchetictureService.model.entities.Profession;
import com.example.companyArchetictureService.model.entities.Space;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceRequest {
    private String name;
    private String description;
    private long unitHeadId;
    private List<Profession> professions;




}
