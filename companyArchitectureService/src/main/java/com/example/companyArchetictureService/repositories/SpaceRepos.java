package com.example.companyArchetictureService.repositories;

import com.example.companyArchetictureService.model.entities.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpaceRepos extends JpaRepository<Space,Long> {

}
