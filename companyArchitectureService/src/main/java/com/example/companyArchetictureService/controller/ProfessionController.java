package com.example.companyArchetictureService.controller;


import com.example.companyArchetictureService.dto.ProfessionDto;
import com.example.companyArchetictureService.exceptions.BadRequestException;
import com.example.companyArchetictureService.exceptions.NotFoundException;
import com.example.companyArchetictureService.helpers.ResponseHandler;
import com.example.companyArchetictureService.services.ProfessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profession")
@CrossOrigin(origins = "*")
public class ProfessionController {

    private ProfessionService professionService;

    private ResponseHandler responseHandler;


    public ProfessionController(ProfessionService professionService, ResponseHandler responseHandler) {
        this.professionService = professionService;
        this.responseHandler = responseHandler;
    }

    //Create new profession
    @PostMapping()
    public ResponseEntity<Object> createProfession(@RequestBody ProfessionDto profession){
        try{
            //Create a space in the database
            professionService.createProfession(profession);
            //Return a success response
            return responseHandler.generateResponse("Profession created successfully", HttpStatus.OK);
        }catch (Exception e ){
            throw new BadRequestException("Error creating profession");
        }
    }

    //Get all professions
    @GetMapping()
    public ResponseEntity<List<ProfessionDto>> getAllProfessions(){
        try{
            //Get all professions
            List<ProfessionDto> professions = professionService.getAllProfessions();
            //Return a success response
            return new ResponseEntity<>(professions, HttpStatus.OK);
        }catch (Exception e ){
            throw new NotFoundException("Error getting professions");
        }
    }

    //Get professions by space id
    @GetMapping("/{id}")
    public ResponseEntity<List<ProfessionDto>> getProfessionsById(@PathVariable long id){
        try{
            //Get a profession by id
            List<ProfessionDto> professions = professionService.getAllProfessionsOfSpace(id);
            //Return a success response
            return new ResponseEntity<>(professions, HttpStatus.OK);

        }catch (Exception e ){
            throw new NotFoundException("Error getting profession");
        }
    }

    //Get a profession by id
    @GetMapping("/profession/{id}")
    public ResponseEntity<ProfessionDto> getProfessionById(@PathVariable long id){
        try{
            //Get a profession by id
            ProfessionDto profession = professionService.getProfessionById(id);
            //Return a success response
            return new ResponseEntity<>(profession, HttpStatus.OK);
        }catch (Exception e ){
            throw new NotFoundException("Error getting profession");
        }
    }

    //Delete a profession by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfessionById(@PathVariable long id){
        try{
            //Delete a profession by id
            professionService.deleteProfessionById(id);
            //Return a success response
            return responseHandler.generateResponse("Profession deleted successfully", HttpStatus.OK);
        }catch (Exception e ){
            throw new BadRequestException("Error deleting profession");
        }
    }

    //Update a profession
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionDto> updateProfession(@RequestBody ProfessionDto profession, @PathVariable long id){
        try{
            //Update the profession and Return a success response
            return new ResponseEntity<>( professionService.updateProfession(profession,id),HttpStatus.OK);
        }catch (Exception e ){
            throw new BadRequestException("Error updating profession");
        }
    }
}
