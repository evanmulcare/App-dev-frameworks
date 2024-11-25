package com.example.pets.services;

import com.example.pets.entities.Household;

import java.util.List;
import java.util.Optional;

public interface HouseholdService {

    Household createHousehold(Household household);
    void deleteHousehold(String eircode);
    List<Household> findAllHouseholds();
    Optional<Household> findHouseholdByEircodeWithPets(String eircode);
    List<Household> findHouseholdsWithNoPets();
}
