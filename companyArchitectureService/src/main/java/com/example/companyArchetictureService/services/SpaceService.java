package com.example.companyArchetictureService.services;


import com.example.common.events.TestEvent;
import com.example.common.events.companyUnitsEvents.UnitCreatedEvent;
import com.example.common.events.helpers.UnitType;
import com.example.companyArchetictureService.dto.SpaceDto;
import com.example.companyArchetictureService.kafka.producer.UnitsEventProducer;
import com.example.companyArchetictureService.model.entities.Space;
import com.example.companyArchetictureService.repositories.SpaceRepos;
import com.example.companyArchetictureService.requests.SpaceRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        UnitCreatedEvent unitCreatedEvent = new UnitCreatedEvent();
        unitCreatedEvent.setUnitId(space1.getId());
        unitCreatedEvent.setUnit(UnitType.SPACE);
        //Send the event to the employee service to create a new chatGrouo
        unitsEventProducer.sendUnitCreatedEvent(new TestEvent());
        //Save the space object
        return spaceRepos.save(space1);
    }

    //Get all spaces
    public List<SpaceDto> getAllSpaces(){
        return spaceRepos.findAll().stream().map(spaceDto::to_dto).toList();
    }

    //Get a space by id
    public SpaceDto getSpaceById(Long id){
        Optional<Space> space = spaceRepos.findById(id);
        if(space.isPresent()){
            return spaceDto.to_dto(space.get());
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
