package com.example.companyArchetictureService.repositories;

import com.example.companyArchetictureService.model.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepos extends JpaRepository<Profession, Long> {

}

