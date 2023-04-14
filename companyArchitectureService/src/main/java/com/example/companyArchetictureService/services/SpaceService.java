package com.example.companyArchetictureService.services;


import com.example.common.events.TestEvent;
import com.example.common.events.companyUnitsEvents.UnitCreatedEvent;
import com.example.common.events.helpers.UnitType;
import com.example.companyArchetictureService.dto.SpaceDto;
import com.example.companyArchetictureService.kafka.producer.UnitsEventProducer;
import com.example.companyArchetictureService.model.entities.Space;
import com.example.companyArchetictureService.repositories.SpaceRepos;
import com.example.companyArchetictureService.requests.SpaceRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class SpaceService {

    private SpaceRepos spaceRepos;

    private SpaceDto spaceDto;

    private UnitsEventProducer unitsEventProducer;

    public SpaceService(SpaceRepos spaceRepos,
                        SpaceDto spaceDto,UnitsEventProducer unitsEventProducer) {
        this.spaceRepos = spaceRepos;
        this.spaceDto = spaceDto;
        this.unitsEventProducer=unitsEventProducer;
    }

    //Create a new space
    public Space createSpace(SpaceDto spaceDto){

        //Convert the request to a space object
        Space space1 = spaceDto.to_entity();

        //Add the space to database and store it in a variable
        Space space2 = spaceRepos.save(space1);

        //Create a new event
        UnitCreatedEvent unitCreatedEvent = new UnitCreatedEvent();

        unitCreatedEvent.setUnitId(space2.getId());

        unitCreatedEvent.setUnit(UnitType.SPACE);
        unitCreatedEvent.setName(space2.getName());
        unitCreatedEvent.setDescription(space2.getDescription());

        //Send the event to the employee service to create a new chatGrouo
        unitCreatedEvent.setDescription("departement.getDescription()");
        //Save the space object
        return space2;
    }

    //Get all spaces
    public List<SpaceDto> getAllSpaces(){

        return spaceRepos.findAll().stream().map(spaceDto::to_dto).toList();
    }

    //Get a space by id
    public List<SpaceDto> getSpaceById(Long id){
        Optional<Space> space = spaceRepos.findById(id);
        List<SpaceDto> spaces = new ArrayList<>();
        if(space.isPresent()){
            spaces.add(spaceDto.to_dto(space.get()));
            return spaces;
        }else {
            return null;
        }
    }

    //Delete a space by id
    public void deleteSpaceById(Long id){
        spaceRepos.deleteById(id);
    }

    //update a space
    public SpaceDto updateSpace(SpaceDto space, long id){
        //Get the element
        Optional<Space> space1 = spaceRepos.findById(id);

        //Update the space
        if(space1.isPresent()){
            space1.get().setName(space.getName());
            space1.get().setDescription(space.getDescription());
            space1.get().setUnitHeadId(space.getUnitHeadId());
        }
        //Save the space
        return spaceDto.to_dto(spaceRepos.save(space1.get()));
    }
}
