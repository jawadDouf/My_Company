package com.example.common.events.companyUnitsEvents;

import com.example.common.events.helpers.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDeletedEvent {

    private long unitId;

    private UnitType unit;


    private String name;


    private String description;
}
