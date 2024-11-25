package com.example.pets.services;

import com.example.pets.entities.Household;

import java.util.List;
import java.util.Optional;

public interface HouseholdService {
    Optional<Household> findHouseholdByEircodeWithPets(String eircode);
    List<Household> findHouseholdsWithNoPets();
}
