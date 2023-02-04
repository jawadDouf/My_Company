package com.example.companyArchetictureService.model.entities;

import com.example.companyArchetictureService.model.superclasses.CompanyUnit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Departement extends CompanyUnit {

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,insertable = false)
    private Profession profession;

    @Column(name = "profession_id")
    private long professionId;

    @OneToMany(mappedBy = "departement")
    private List<MiniDeps> miniDeps;
}
