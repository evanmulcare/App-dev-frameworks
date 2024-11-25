package com.example.pets.services;

import com.example.pets.entities.Household;
import com.example.pets.repo.HouseholdRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepo householdRepo;

    @Autowired
    public HouseholdServiceImpl(HouseholdRepo householdRepo) {
        this.householdRepo = householdRepo;
    }

    @Override
    public Optional<Household> findHouseholdByEircodeWithPets(String eircode) {
        return householdRepo.findByEircodeWithPetsEagerlyLoaded(eircode);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepo.findHouseholdsWithNoPets();
    }

}
