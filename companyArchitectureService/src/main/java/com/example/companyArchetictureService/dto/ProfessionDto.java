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

    private long id;
    private String name;
    private String description;
    private long unitHeadId;
    private List<DepartementDto> departements;
    private long spaceId;

    public ProfessionDto to_dto(Profession profession){
        return ProfessionDto.builder()
                .id(profession.getId())
                .name(profession.getName())
                .description(profession.getDescription())
                .unitHeadId(profession.getUnitHeadId())
                .departements(profession.getDepartements().stream().map(new DepartementDto()::to_dto).toList())
                .spaceId(profession.getSpaceId())
                .build();
    }


    public Profession to_entity(){
        return Profession.builder()
                .id(this.getId())
                .name(this.getName())
                .description(this.getDescription())
                .unitHeadId(this.getUnitHeadId())
                .departements(this.getDepartements().stream().map(DepartementDto::to_entity).toList())
                .spaceId(this.getSpaceId())
                .build();
    }


}
