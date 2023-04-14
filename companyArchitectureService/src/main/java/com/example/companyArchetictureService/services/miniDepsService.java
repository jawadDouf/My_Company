package com.example.companyArchetictureService.services;


import com.example.common.events.companyUnitsEvents.UnitCreatedEvent;
import com.example.common.events.helpers.UnitType;
import com.example.companyArchetictureService.dto.DepartementDto;
import com.example.companyArchetictureService.dto.MiniDepsDto;
import com.example.companyArchetictureService.kafka.producer.UnitsEventProducer;
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

    private UnitsEventProducer unitsEventProducer;


    public miniDepsService(com.example.companyArchetictureService.repositories.miniDepsRepo miniDepsRepo, MiniDepsDto miniDepsDto, UnitsEventProducer unitsEventProducer) {
        this.miniDepsRepo = miniDepsRepo;
        this.miniDepsDto = miniDepsDto;
        this.unitsEventProducer = unitsEventProducer;
    }

    //Create a new miniDeps
    public MiniDeps createMiniDeps(MiniDepsDto miniDepsDto){
        //Convert dto to entity
        MiniDeps miniDeps1 = miniDepsDto.to_entity();
        //Add the departement to database and store it in a variable
        MiniDeps miniDeps = miniDepsRepo.save(miniDeps1);

        //Create a new event
        UnitCreatedEvent unitCreatedEvent = new UnitCreatedEvent();

        unitCreatedEvent.setUnitId(miniDeps.getId());

        unitCreatedEvent.setUnit(UnitType.MINIDEPS);
        unitCreatedEvent.setName(miniDeps.getName());
        unitCreatedEvent.setDescription(miniDeps.getDescription());

        //Send the event to the employee service to create a new chatGrouo
        unitCreatedEvent.setDescription("departement.getDescription()");
        //Save the profession object
        return miniDeps;
    }


    //Get all miniDeps
    public List<MiniDepsDto> getAllMiniDeps(){
        return miniDepsRepo.findAll().stream().map(miniDepsDto::to_dto).toList();
    }

    //Get a miniDeps by id
    public MiniDepsDto getMiniDepsById(Long id){
        return miniDepsDto.to_dto(miniDepsRepo.findById(id).get());
    }

    //Get all miniDeps of a departement
    public List<MiniDepsDto> getAllMiniDepsOfDepartement(long departementId){
        return miniDepsRepo.findAllByDepartementId(departementId).stream().map(miniDepsDto::to_dto).toList();
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
