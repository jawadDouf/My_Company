package com.example.common.events.companyUnitsEvents;

import com.example.common.events.helpers.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitCreatedEvent {


    private long unitId;

    private UnitType unit;


    private String name;


    private String description;


}
