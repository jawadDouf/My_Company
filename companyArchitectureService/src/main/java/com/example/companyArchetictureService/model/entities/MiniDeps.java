package com.example.companyArchetictureService.model.entities;

import com.example.companyArchetictureService.model.superclasses.CompanyUnit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class MiniDeps extends CompanyUnit {





    @ManyToOne()
    @JoinColumn(nullable = false,updatable = false,insertable = false)
    private Departement departement;


    @Column(name = "departement_id")
    private long departementId;

}
