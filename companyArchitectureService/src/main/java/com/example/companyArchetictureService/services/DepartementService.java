package com.example.companyArchetictureService.services;


import com.example.common.events.companyUnitsEvents.UnitCreatedEvent;
import com.example.common.events.helpers.UnitType;
import com.example.companyArchetictureService.dto.DepartementDto;
import com.example.companyArchetictureService.dto.ProfessionDto;
import com.example.companyArchetictureService.kafka.producer.UnitsEventProducer;
import com.example.companyArchetictureService.model.entities.Departement;
import com.example.companyArchetictureService.model.entities.Profession;
import com.example.companyArchetictureService.repositories.DepartementRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    private DepartementRepo departementRepo;
    private DepartementDto departementDto;

    private UnitsEventProducer unitsEventProducer;

    public DepartementService(DepartementRepo departementRepo, DepartementDto departementDto, UnitsEventProducer unitsEventProducer) {
        this.departementRepo = departementRepo;
        this.departementDto = departementDto;
        this.unitsEventProducer = unitsEventProducer;
    }

    //Create a new departement
    public Departement createDepartement(DepartementDto departementDto){
        //Convert the request to a departement object
        Departement departement1 = departementDto.to_entity();


        //Add the departement to database and store it in a variable
        Departement departement = departementRepo.save(departement1);

        //Create a new event
        UnitCreatedEvent unitCreatedEvent = new UnitCreatedEvent();

        unitCreatedEvent.setUnitId(departement.getId());

        unitCreatedEvent.setUnit(UnitType.DEPARTEMENT);
        unitCreatedEvent.setName(departement.getName());
        unitCreatedEvent.setDescription("departement.getDescription()");

        //Send the event to the employee service to create a new chatGrouo
        unitsEventProducer.sendUnitCreatedEvent(unitCreatedEvent);
        //Save the profession object
        return departement;
    }

    //get all departements of a profession
    public List<DepartementDto> getAllDepartementsOfProfession(long professionId){
        return departementRepo.findAllByProfessionId(professionId).stream().map(departementDto::to_dto).toList();
    }
    //Get all departements
    public List<DepartementDto> getAllDepartements(){
        return departementRepo.findAll().stream().map(departementDto::to_dto).toList();
    }

    //Get a departement by id
    public DepartementDto getDepartementById(Long id) {
        return departementDto.to_dto(departementRepo.findById(id).get());
    }

    //Delete a departement by id
    public void deleteDepartementById(Long id){
        departementRepo.deleteById(id);
    }

    //update a departement
    public DepartementDto updateDepartement(DepartementDto departementDto1, long id){
        //Get the element
        Optional<Departement> departement = departementRepo.findById(id);

        //Update the space
        if(departement.isPresent()){

            departement.get().setName(departementDto1.getName());
            departement.get().setDescription(departementDto1.getDescription());
            departement.get().setUnitHeadId(departementDto1.getUnitHeadId());
        }
        //Save the space
        return departementDto.to_dto(departementRepo.save(departement.get()));
    }
}
