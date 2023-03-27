package com.example.companyArchetictureService.controller;


import com.example.companyArchetictureService.dto.SpaceDto;
import com.example.companyArchetictureService.exceptions.BadRequestException;
import com.example.companyArchetictureService.exceptions.NotFoundException;
import com.example.companyArchetictureService.helpers.ResponseHandler;
import com.example.companyArchetictureService.kafka.producer.UnitsEventProducer;
import com.example.companyArchetictureService.model.entities.Space;
import com.example.companyArchetictureService.repositories.SpaceRepos;
import com.example.companyArchetictureService.requests.SpaceRequest;
import com.example.companyArchetictureService.services.SpaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    private SpaceService spaceService;

    private ResponseHandler responseHandler;





    public SpaceController(SpaceService spaceService,
                            ResponseHandler responseHandler) {

        this.spaceService = spaceService;
        this.responseHandler=responseHandler;

    }


    //Create new space
    @PostMapping()
    public ResponseEntity<Object> createSpace(@RequestBody SpaceDto space){
      try{
          //Create a space in the database
          spaceService.createSpace(space);
          //Return a success response
          return responseHandler.generateResponse("Space created successfully", HttpStatus.OK);
      }catch (Exception e ){
          throw new BadRequestException(e.getMessage());
      }

      }


      //Update a space
      @PutMapping("/{id}")
        public ResponseEntity<SpaceDto> updateSpace(@RequestBody SpaceDto space, @PathVariable long id){
            try{

                //Update the space and Return a success response
                return new ResponseEntity<>( spaceService.updateSpace(space,id),HttpStatus.OK);
            }catch (Exception e ){
                throw new NotFoundException("Error updating space");
            }
        }


        //Get all spaces
        @GetMapping("")
           public ResponseEntity<List<SpaceDto>> getAllSpaces(){
                try{
                    //Get all spaces and Return a success response
                    return new ResponseEntity<>(spaceService.getAllSpaces(),HttpStatus.OK);
                }catch (Exception e ){
                    throw new NotFoundException("Error getting spaces");
                }
            }

        //Get a space by id
        @GetMapping("/{id}")
        public ResponseEntity<SpaceDto> getSpaceById(@PathVariable long id){
            try{
                //Get a space by id and Return a success response
                return new ResponseEntity<>(spaceService.getSpaceById(id),HttpStatus.OK);
            }catch (Exception e ){
                throw new NotFoundException("Error getting space");
            }
        }

        //Delete a space by id
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deleteSpaceById(@PathVariable long id){
            try{
                //Delete a space by id and Return a success response
                spaceService.deleteSpaceById(id);
                return responseHandler.generateResponse("Space deleted successfully", HttpStatus.OK);
            }catch (Exception e ){
                throw new BadRequestException("Error deleting space");
            }
        }





}
