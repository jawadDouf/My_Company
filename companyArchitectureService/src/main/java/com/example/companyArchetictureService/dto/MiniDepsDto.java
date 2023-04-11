package com.example.companyArchetictureService.dto;


import com.example.companyArchetictureService.model.entities.MiniDeps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Builder
@Component
@Scope("singleton")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiniDepsDto {


    private long id;

    private String name;
    private String description;
    private long unitHeadId;
    private long departementId;


    //Turn a MiniDeps entity into a MiniDepsDto
    public  MiniDepsDto to_dto(MiniDeps miniDeps){
        return MiniDepsDto.builder()
                .id(miniDeps.getId())
                .name(miniDeps.getName())
                .description(miniDeps.getDescription())
                .unitHeadId(miniDeps.getUnitHeadId())
                .departementId(miniDeps.getDepartement().getId())
                .build();
    }



    //Turn dto to entity
    public MiniDeps to_entity(){
        return MiniDeps.builder()
                .id(this.getId())
                .name(this.getName())
                .description(this.getDescription())
                .unitHeadId(this.getUnitHeadId())
                .departementId(this.getDepartementId())
                .build();
    }


}
