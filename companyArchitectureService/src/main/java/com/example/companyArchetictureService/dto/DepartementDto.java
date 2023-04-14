package com.example.companyArchetictureService.dto;


import com.example.companyArchetictureService.model.entities.Departement;
import com.example.companyArchetictureService.model.entities.MiniDeps;
import com.example.companyArchetictureService.model.entities.Profession;
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
public class DepartementDto {



    private long id;
    private String name;
    private String description;
    private long unitHeadId;
    private List<MiniDepsDto> miniDeps;

    private long profession_id;

    public DepartementDto to_dto(Departement departement){
        return DepartementDto.builder()
                .id(departement.getId())
                .name(departement.getName())
                .description(departement.getDescription())
                .unitHeadId(departement.getUnitHeadId())
                .miniDeps(departement.getMiniDeps().stream().map(new MiniDepsDto()::to_dto).toList())
                .profession_id(departement.getProfessionId())
                .build();
    }


    public Departement to_entity(){
        return Departement.builder()
                .id(this.getId())
                .name(this.getName())
                .description(this.getDescription())
                .unitHeadId(this.getUnitHeadId())
                .professionId(this.getProfession_id())
                .build();
    }

}
