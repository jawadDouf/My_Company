package com.example.companyArchetictureService.controller;


import com.example.companyArchetictureService.dto.DepartementDto;
import com.example.companyArchetictureService.dto.ProfessionDto;
import com.example.companyArchetictureService.exceptions.BadRequestException;
import com.example.companyArchetictureService.exceptions.NotFoundException;
import com.example.companyArchetictureService.helpers.ResponseHandler;
import com.example.companyArchetictureService.services.DepartementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departement")
@CrossOrigin(origins = "*")
public class DepartementController {

    private DepartementService departementService;

    private ResponseHandler responseHandler;

    public DepartementController(DepartementService departementService, ResponseHandler responseHandler) {
        this.departementService = departementService;
        this.responseHandler = responseHandler;
    }

    //Create new departement
    @PostMapping()
    public ResponseEntity<Object> createDepartement(@RequestBody DepartementDto departementDto) {
        try {
            //Create a space in the database
            departementService.createDepartement(departementDto);
            //Return a success response
            return responseHandler.generateResponse("Departement created successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    //Get all departements
    @GetMapping()
    public ResponseEntity<List<DepartementDto>> getAllDepartements() {
        try {
            //Get all professions
            List<DepartementDto> departements = departementService.getAllDepartements();
            //Return a success response
            return new ResponseEntity<>(departements, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Error getting departements");
        }
    }



    //Get departements of a profession
    @GetMapping("/{id}")
    public ResponseEntity<List<DepartementDto>> getDepartementsOfProfession(@PathVariable long id) {
        try {
            //Get all professions
            List<DepartementDto> departements = departementService.getAllDepartementsOfProfession(id);
            //Return a success response
            return new ResponseEntity<>(departements, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Error getting departements");
        }
    }

    //Get a departement by id
    @GetMapping("/departement/{id}")
    public ResponseEntity<DepartementDto> getDepartementById(@PathVariable long id) {
        try {
            //Get a profession by id
            DepartementDto departement = departementService.getDepartementById(id);
            //Return a success response
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Error getting departements");
        }
    }

    //Delete a departement by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartementById(@PathVariable long id) {
        try {
            //Delete a departement by id
            departementService.deleteDepartementById(id);
            //Return a success response
            return responseHandler.generateResponse("Departement deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Error deleting departements");
        }
    }

    //Update a departement by id
    @PutMapping("/{id}")
    public ResponseEntity<DepartementDto> updateDepartement(@RequestBody DepartementDto departementDto, @PathVariable long id) {
        try {
            //Update the profession and Return a success response
            return new ResponseEntity<>(departementService.updateDepartement(departementDto, id), HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Error deleting departements");
        }

    }
}