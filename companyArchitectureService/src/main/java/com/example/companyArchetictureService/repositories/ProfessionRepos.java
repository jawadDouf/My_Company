package com.example.companyArchetictureService.repositories;

import com.example.companyArchetictureService.model.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionRepos extends JpaRepository<Profession, Long> {

    // get all professions of a space
    List<Profession> findAllBySpaceId(long spaceId);
}

