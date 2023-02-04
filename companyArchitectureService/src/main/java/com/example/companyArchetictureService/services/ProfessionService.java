package com.example.companyArchetictureService.services;


import com.example.companyArchetictureService.dto.ProfessionDto;
import com.example.companyArchetictureService.dto.SpaceDto;
import com.example.companyArchetictureService.model.entities.Profession;
import com.example.companyArchetictureService.model.entities.Space;
import com.example.companyArchetictureService.repositories.ProfessionRepos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionService {

    private ProfessionRepos professionRepos;

    private ProfessionDto professionDto;


    public ProfessionService(ProfessionRepos professionRepos, ProfessionDto professionDto) {
        this.professionRepos = professionRepos;
        this.professionDto = professionDto;
    }

    //Create a new profession
    public Profession createProfession(ProfessionDto professionDto){
        //Convert the request to a profession object
        Profession profession1 = professionDto.to_entity();
        //Save the profession object
        return professionRepos.save(profession1);
    }

    //Get all professions
    public List<ProfessionDto> getAllProfessions(){
        return professionRepos.findAll().stream().map(professionDto::to_dto).toList();
    }

    //Get a profession by id
    public ProfessionDto getProfessionById(Long id){
        return professionDto.to_dto(professionRepos.findById(id).get());
    }

    //Delete a profession by id
    public void deleteProfessionById(Long id){
        professionRepos.deleteById(id);
    }

    //update a profession
    public ProfessionDto updateProfession(ProfessionDto professionDto1, long id){
        //Get the element
        Optional<Profession> profession = professionRepos.findById(id);

        //Update the space
        if(profession.isPresent()){

            profession.get().setName(professionDto1.getName());
            profession.get().setDescription(professionDto1.getDescription());
            profession.get().setUnitHeadId(professionDto1.getUnitHeadId());
        }
        //Save the space
        return professionDto.to_dto(professionRepos.save(profession.get()));
    }
}
