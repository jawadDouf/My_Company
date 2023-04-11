package com.example.companyArchetictureService.controller;

import com.example.companyArchetictureService.dto.MiniDepsDto;
import com.example.companyArchetictureService.exceptions.BadRequestException;
import com.example.companyArchetictureService.exceptions.NotFoundException;
import com.example.companyArchetictureService.helpers.ResponseHandler;
import com.example.companyArchetictureService.model.entities.MiniDeps;
import com.example.companyArchetictureService.services.miniDepsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miniDeps")
@CrossOrigin(origins = "*")
public class miniDepsController {



    private miniDepsService miniDepsService;

    private ResponseHandler responseHandler;


    public miniDepsController(miniDepsService miniDepsService, ResponseHandler responseHandler) {
        this.miniDepsService = miniDepsService;
        this.responseHandler = responseHandler;
    }


    //Create a new miniDeps
    @PostMapping()
    public ResponseEntity<Object> createMiniDeps(@RequestBody MiniDepsDto miniDeps){
        try{
            //Create a space in the database
            miniDepsService.createMiniDeps(miniDeps);
            //Return a success response
            return responseHandler.generateResponse("MiniDeps created successfully", HttpStatus.OK);
        }catch (Exception e ){
           throw new BadRequestException("Error creating miniDeps");
        }
    }

    //Get all miniDeps
    @GetMapping()
    public ResponseEntity<List<MiniDepsDto>> getAllMiniDeps(){
        try{
            //Get all miniDeps
            List<MiniDepsDto> miniDeps = miniDepsService.getAllMiniDeps();
            //Return a success response
            return new ResponseEntity<>(miniDeps, HttpStatus.OK);
        }catch (Exception e ){
            throw new NotFoundException("Error getting miniDeps");
        }
    }


    //Get a miniDeps by id
    @GetMapping("/departement/{id}")
    public ResponseEntity<MiniDepsDto> getMiniDepsById(@PathVariable("id") Long id){
        try{
            //Get the miniDeps
            MiniDepsDto miniDeps = miniDepsService.getMiniDepsById(id);
            //Return a success response
            return new ResponseEntity<>(miniDeps, HttpStatus.OK);
        }catch (Exception e ){
            throw new NotFoundException("Error getting miniDeps");
        }
    }

    //Get all miniDeps of a departement
    @GetMapping("/{id}")
    public ResponseEntity<List<MiniDepsDto>> getAllMiniDepsOfDepartement(@PathVariable("id") Long id){
        try{
            //Get the miniDeps
            List<MiniDepsDto> miniDeps = miniDepsService.getAllMiniDepsOfDepartement(id);
            //Return a success response
            return new ResponseEntity<>(miniDeps, HttpStatus.OK);
        }catch (Exception e ){
            throw new NotFoundException("Error getting miniDeps");
        }
    }


    //Delete a miniDeps by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMiniDepsById(@PathVariable("id") Long id){
        try{
            //Delete the miniDeps
            miniDepsService.deleteMiniDepsById(id);
            //Return a success response
            return responseHandler.generateResponse("MiniDeps deleted successfully", HttpStatus.OK);
        }catch (Exception e ){
            throw new BadRequestException("Error deleting miniDeps");
        }
    }

    //Update a miniDeps
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMiniDeps(@RequestBody MiniDepsDto miniDeps, @PathVariable("id") Long id){
        try{
            //Update the miniDeps
            miniDepsService.updateMiniDeps(miniDeps, id);
            //Return a success response
            return new ResponseEntity<>("MiniDeps updated successfully", HttpStatus.OK);
        }catch (Exception e ){
            throw new BadRequestException("Error updating miniDeps");
        }
    }


}
