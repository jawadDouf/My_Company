package com.example.companyArchetictureService.repositories;

import com.example.companyArchetictureService.model.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartementRepo extends JpaRepository<Departement, Long> {


    // get all departements of a profession
    List<Departement> findAllByProfessionId(long professionId);
}

