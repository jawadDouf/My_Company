package com.example.companyArchetictureService.dto;


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
public class SpaceDto {


    private long id;
    private String name;
    private String description;
    private long unitHeadId;
    private List<ProfessionDto> professions;


    public SpaceDto to_dto(Space space){
        return SpaceDto.builder()
                .id(space.getId())
                .name(space.getName())
                .description(space.getDescription())
                .unitHeadId(space.getUnitHeadId())
                .professions(space.getProfessions().stream().map(new ProfessionDto()::to_dto).toList())
                .build();
    }


    public Space to_entity(){
        return Space.builder()
                .id(this.getId())
                .name(this.getName())
                .description(this.getDescription())
                .unitHeadId(this.getUnitHeadId())
                .build();
    }


}
