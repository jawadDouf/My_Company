package com.example.companyArchetictureService.services;


import com.example.companyArchetictureService.dto.DepartementDto;
import com.example.companyArchetictureService.dto.MiniDepsDto;
import com.example.companyArchetictureService.model.entities.Departement;
import com.example.companyArchetictureService.model.entities.MiniDeps;
import org.springframework.stereotype.Service;

import com.example.companyArchetictureService.repositories.miniDepsRepo;

import java.util.List;
import java.util.Optional;

@Service
public class miniDepsService {



    private miniDepsRepo miniDepsRepo;

    private MiniDepsDto miniDepsDto;


    public miniDepsService(miniDepsRepo miniDepsRepo, MiniDepsDto miniDepsDto) {
        this.miniDepsRepo = miniDepsRepo;
        this.miniDepsDto = miniDepsDto;
    }



    //Create a new miniDeps
    public MiniDeps createMiniDeps(MiniDepsDto miniDepsDto){
        //Convert dto to entity
        MiniDeps miniDeps = miniDepsDto.to_entity();
        //Save the entity
        return miniDepsRepo.save(miniDeps);
    }


    //Get all miniDeps
    public List<MiniDepsDto> getAllMiniDeps(){
        return miniDepsRepo.findAll().stream().map(miniDepsDto::to_dto).toList();
    }

    //Get a miniDeps by id
    public MiniDepsDto getMiniDepsById(Long id){
        return miniDepsDto.to_dto(miniDepsRepo.findById(id).get());
    }


    //Delete a miniDeps by id
    public void deleteMiniDepsById(Long id){
        miniDepsRepo.deleteById(id);
    }

    //Update a miniDeps
    public MiniDepsDto updateMiniDeps(MiniDepsDto miniDepsDto, long id){
        //Get the element
        Optional<MiniDeps> miniDeps = miniDepsRepo.findById(id);

        //Update the space
        if(miniDeps.isPresent()){
            miniDeps.get().setName(miniDepsDto.getName());
            miniDeps.get().setDescription(miniDepsDto.getDescription());
            miniDeps.get().setUnitHeadId(miniDepsDto.getUnitHeadId());
            miniDeps.get().setDepartementId(miniDepsDto.getDepartementId());
        }
        //Save the space
        return miniDepsDto.to_dto(miniDepsRepo.save(miniDeps.get()));
    }



}
