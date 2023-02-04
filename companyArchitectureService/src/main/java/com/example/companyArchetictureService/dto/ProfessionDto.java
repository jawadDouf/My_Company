package com.example.companyArchetictureService.dto;


import com.example.companyArchetictureService.model.entities.Departement;

import com.example.companyArchetictureService.model.entities.Profession;
import com.example.companyArchetictureService.model.entities.Space;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Component
@Scope("singleton")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionDto {

    private String name;
    private String description;
    private long unitHeadId;
    private List<Departement> departements;
    private long spaceId;

    public ProfessionDto to_dto(Profession profession){
        return ProfessionDto.builder()
                .name(profession.getName())
                .description(profession.getDescription())
                .unitHeadId(profession.getUnitHeadId())
                .departements(profession.getDepartements())
                .build();
    }


    public Profession to_entity(){
        return Profession.builder()
                .name(this.getName())
                .description(this.getDescription())
                .unitHeadId(this.getUnitHeadId())
                .departements(this.getDepartements())
                .spaceId(this.getSpaceId())
                .build();
    }


}
