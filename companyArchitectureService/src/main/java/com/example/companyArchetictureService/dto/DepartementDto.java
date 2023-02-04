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

    private String name;
    private String description;
    private long unitHeadId;
    private List<MiniDeps> miniDeps;

    private long profession_id;

    public DepartementDto to_dto(Departement departement){
        return DepartementDto.builder()
                .name(departement.getName())
                .description(departement.getDescription())
                .unitHeadId(departement.getUnitHeadId())
                .miniDeps(departement.getMiniDeps())
                .build();
    }


    public Departement to_entity(){
        return Departement.builder()
                .name(this.getName())
                .description(this.getDescription())
                .unitHeadId(this.getUnitHeadId())
                .miniDeps(this.getMiniDeps())
                .professionId(this.getProfession_id())
                .build();
    }

}
