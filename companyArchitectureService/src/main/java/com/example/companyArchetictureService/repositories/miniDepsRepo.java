package com.example.companyArchetictureService.repositories;

import com.example.companyArchetictureService.model.entities.MiniDeps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface miniDepsRepo extends JpaRepository<MiniDeps,Long> {



    //get all miniDeps of a departement
    List<MiniDeps> findAllByDepartementId(long departementId);
}

